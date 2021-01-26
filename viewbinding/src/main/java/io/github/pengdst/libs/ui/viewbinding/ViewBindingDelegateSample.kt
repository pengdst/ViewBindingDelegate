package io.github.pengdst.libs.ui.viewbinding

import android.util.Log

/**
 *
 * Created by Pengkuh Dwi Septiandi on 1/26/21.
 *
 * Github    : https://github.com/pengdst
 * Gitlab    : https://gitlab.com/pengdst
 * LinkedIn  : https://linkedin.com/in/pengkuh-dst/
 *
 */

class ViewBindingDelegateSample {

    companion object Builder {
        private const val TAG = "ViewBindingDelegateSamp"

        fun e(message: String?){
            Log.e(TAG, message.toString())
        }
    }

}