package com.alanhu.tools.softinputvisibilitylistener

import android.os.Build
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.alanhu.tools.softinputvisibilitylistener.handler.SoftInputVisibilityListenerHandler
import com.alanhu.tools.softinputvisibilitylistener.handler.impl.Api23Impl
import com.alanhu.tools.softinputvisibilitylistener.handler.impl.DefaultImpl

val FragmentActivity.isSoftInputVisible: Boolean
    get() = softInputVisibilityListenerHandler.isSoftInputVisible(this)

val Fragment.isSoftInputVisible: Boolean
    get() = activity?.isSoftInputVisible ?: false

/**
 * lifecycle aware, auto unregister on Activity#onDestroy
 */
fun FragmentActivity.registerSoftInputVisibilityListener(
    listener: SoftInputVisibilityListener,
): SoftInputVisibilityUnregistrar =
    softInputVisibilityListenerHandler.handle(this, listener).also {
        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                it.unregister()
            }
        })
    }

/**
 * lifecycle aware, auto unregister on Fragment#onDestroyView
 */
fun Fragment.registerSoftInputVisibilityListener(
    listener: SoftInputVisibilityListener,
): SoftInputVisibilityUnregistrar =
    softInputVisibilityListenerHandler.handle(this.requireActivity(), listener).also {
        viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                it.unregister()
            }
        })
    }

private val softInputVisibilityListenerHandler: SoftInputVisibilityListenerHandler
    get() = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> Api23Impl()
        else -> DefaultImpl()
    }