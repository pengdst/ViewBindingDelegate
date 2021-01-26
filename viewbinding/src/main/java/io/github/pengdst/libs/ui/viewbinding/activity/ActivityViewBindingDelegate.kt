package io.github.pengdst.libs.ui.viewbinding.activity

import android.app.Activity
import android.view.LayoutInflater
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import io.github.pengdst.libs.ui.viewbinding.fragment.FragmentViewBindingDelegate
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

class ActivityViewBindingDelegate<T : ViewBinding>(private val bindingClass: Class<T>) :
    ReadOnlyProperty<Activity, T> {

    companion object Extension {
        @MainThread
        inline fun <reified T : ViewBinding> Fragment.viewBindings(): FragmentViewBindingDelegate<T> =
            FragmentViewBindingDelegate(
                fragment = this,
                viewBindingClazz = T::class.java
            )
    }

    /**
     * initiate variable for binding view
     */
    private var binding: T? = null

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        binding?.let { return it }

        /**
         * inflate View class
         */
        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)

        /**
         * Bind layout
         */
        val invokeLayout = inflateMethod.invoke(null, thisRef.layoutInflater) as T

        /**
         * Set the content view
         */
        thisRef.setContentView(invokeLayout.root)

        return invokeLayout.also { this.binding = it }
    }
}
