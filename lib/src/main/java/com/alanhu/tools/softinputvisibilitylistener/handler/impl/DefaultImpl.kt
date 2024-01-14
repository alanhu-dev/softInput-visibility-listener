package com.alanhu.tools.softinputvisibilitylistener.handler.impl

import android.graphics.Rect
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
import androidx.fragment.app.FragmentActivity
import com.alanhu.tools.softinputvisibilitylistener.SoftInputVisibilityListener
import com.alanhu.tools.softinputvisibilitylistener.SoftInputVisibilityUnregistrar
import com.alanhu.tools.softinputvisibilitylistener.handler.SoftInputVisibilityListenerHandler
import com.alanhu.tools.softinputvisibilitylistener.util.maxWindowHeightWithoutSystemBars

internal class DefaultImpl : SoftInputVisibilityListenerHandler() {
    override fun handle(
        activity: FragmentActivity, listener: SoftInputVisibilityListener,
    ): SoftInputVisibilityUnregistrar {
        val softInputMode = activity.window.attributes.softInputMode and
                WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST

        // The window will not be resized in case of SOFT_INPUT_ADJUST_NOTHING
        require(softInputMode and SOFT_INPUT_ADJUST_NOTHING != SOFT_INPUT_ADJUST_NOTHING) {
            "Activity window SoftInputMethod is SOFT_INPUT_ADJUST_NOTHING. " +
                    "In this case window will not be resized!"
        }

        return super.handle(activity, listener)
    }

    override fun isSoftInputVisible(activity: FragmentActivity): Boolean {
        val screenHeight = activity.maxWindowHeightWithoutSystemBars

        val windowVisibleRect = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(windowVisibleRect)

        val softInputHeight = screenHeight - windowVisibleRect.height()
        return softInputHeight > screenHeight * SOFT_INPUT_MIN_HEIGHT_RATIO
    }

    companion object {
        private const val SOFT_INPUT_MIN_HEIGHT_RATIO = .15
    }
}