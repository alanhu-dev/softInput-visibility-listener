package com.alanhu.tools.softinputvisibilitylistener

import android.app.Activity
import android.view.ViewTreeObserver
import java.lang.ref.WeakReference

class SoftInputVisibilityUnregistrar internal constructor(
    activity: Activity, listener: ViewTreeObserver.OnGlobalLayoutListener,
) {
    private val activityWeakRef = WeakReference(activity)
    private val listenerWeakRef = WeakReference(listener)

    fun unregister() {
        listenerWeakRef.get()?.let {
            activityWeakRef.get()?.window?.decorView
                ?.viewTreeObserver?.removeOnGlobalLayoutListener(it)
        }
        activityWeakRef.clear()
        listenerWeakRef.clear()
    }
}