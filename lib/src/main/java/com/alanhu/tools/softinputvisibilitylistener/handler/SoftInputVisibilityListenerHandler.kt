package com.alanhu.tools.softinputvisibilitylistener.handler

import android.view.ViewTreeObserver
import androidx.fragment.app.FragmentActivity
import com.alanhu.tools.softinputvisibilitylistener.SoftInputVisibilityListener
import com.alanhu.tools.softinputvisibilitylistener.SoftInputVisibilityUnregistrar

internal abstract class SoftInputVisibilityListenerHandler {
    open fun handle(
        activity: FragmentActivity, listener: SoftInputVisibilityListener,
    ): SoftInputVisibilityUnregistrar {

        var wasVisible = false
        val onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            val isVisible = isSoftInputVisible(activity)
            if (wasVisible != isVisible) {
                wasVisible = isVisible
                listener.onVisibilityChanged(isVisible)
            }
        }

        activity.window.decorView
            .viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)

        return SoftInputVisibilityUnregistrar(activity, onGlobalLayoutListener)
    }

    abstract fun isSoftInputVisible(activity: FragmentActivity): Boolean
}