package io.github.pengdst.libs.ui.extensions

import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import io.github.pengdst.libs.ui.viewbinding.activity.ActivityViewBindingDelegate
import io.github.pengdst.libs.ui.viewbinding.fragment.FragmentViewBindingDelegate

/**
 *
 * Created by Pengkuh Dwi Septiandi on 1/26/21.
 *
 * Github    : https://github.com/pengdst
 * Gitlab    : https://gitlab.com/pengdst
 * LinkedIn  : https://linkedin.com/in/pengkuh-dst/
 *
 */


@MainThread
inline fun <reified T : ViewBinding> Fragment.viewBindings(): FragmentViewBindingDelegate<T> =
    FragmentViewBindingDelegate(
        fragment = this,
        viewBindingClazz = T::class.java
    )

@MainThread
inline fun <reified T : ViewBinding> AppCompatActivity.viewBindings(): ActivityViewBindingDelegate<T> =
    ActivityViewBindingDelegate(T::class.java)