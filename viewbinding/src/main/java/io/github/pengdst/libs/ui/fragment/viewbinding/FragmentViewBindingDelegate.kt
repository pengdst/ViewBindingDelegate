package io.github.pengdst.libs.ui.fragment.viewbinding

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/**
 *
 * Created by Pengkuh Dwi Septiandi on 1/26/21.
 *
 * Github    : https://github.com/pengdst
 * Gitlab    : https://gitlab.com/pengdst
 * LinkedIn  : https://linkedin.com/in/pengkuh-dst/
 *
 */
class FragmentViewBindingDelegate<T : ViewBinding>(
    private val fragment: Fragment,
    viewbindingInflate: ((LayoutInflater) -> T)? = null,
    viewBindingClazz: Class<T>? = null
) : ReadOnlyProperty<Fragment, T> {

    companion object {
        @MainThread
        inline fun <reified T : ViewBinding> Fragment.viewBindings(): FragmentViewBindingDelegate<T> =
            FragmentViewBindingDelegate(
                fragment = this,
                viewBindingClazz = T::class.java
            )
    }

    private var binding: T? = null
    private val inflateMethod: (LayoutInflater)->T

    init {
        require(viewbindingInflate != null || viewBindingClazz != null) {
            "Both viewbindingInflate and viewBindingClazz are null. Please provide at least one."
        }

        inflateMethod = viewbindingInflate ?: run {
            val method by lazy(LazyThreadSafetyMode.NONE) { viewBindingClazz!!.getMethod("inflate", LayoutInflater::class.java) }

            @Suppress("UNCHECKED_CAST")
            fun(inflater: LayoutInflater): T = method.invoke(null, inflater) as T
        }

        fragment.lifecycle.addObserver(FragmentLifecycleObserver())
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        binding?.let { return it }

        check(fragment.viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)){
            "Attemp to get viewBinding when fragment view is destroyed"
        }

        return inflateMethod(thisRef.layoutInflater).also { binding = it }
    }

    private inner class FragmentLifecycleObserver : DefaultLifecycleObserver {

        override fun onCreate(owner: LifecycleOwner) {
            fragment.viewLifecycleOwnerLiveData.observe(fragment){ viewLifecycleOwner: LifecycleOwner? ->
                viewLifecycleOwner ?: return@observe

                val viewLifecycleObserver = object : DefaultLifecycleObserver{
                    override fun onDestroy(owner: LifecycleOwner) {
                        viewLifecycleOwner.lifecycle.removeObserver(this)
                        Handler(Looper.getMainLooper()).post { binding = null }
                    }
                }

                viewLifecycleOwner.lifecycle.addObserver(viewLifecycleObserver)
            }
        }

        override fun onDestroy(owner: LifecycleOwner) {
            fragment.lifecycle.removeObserver(this)
            binding = null
        }
    }
}