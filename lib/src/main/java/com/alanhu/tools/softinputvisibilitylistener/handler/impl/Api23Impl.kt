package com.alanhu.tools.softinputvisibilitylistener.handler.impl

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity
import com.alanhu.tools.softinputvisibilitylistener.handler.SoftInputVisibilityListenerHandler

@RequiresApi(Build.VERSION_CODES.M)
internal class Api23Impl : SoftInputVisibilityListenerHandler() {
    override fun isSoftInputVisible(activity: FragmentActivity): Boolean {
        return ViewCompat.getRootWindowInsets(activity.window.decorView)
            ?.isVisible(WindowInsetsCompat.Type.ime()) == true
    }
}