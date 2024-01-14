package com.alanhu.tools.softinputvisibilitylistener.util

import android.app.Activity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.window.layout.WindowMetricsCalculator

val windowMetricsCalculator by lazy { WindowMetricsCalculator.getOrCreate() }

val Activity.maxWindowHeight: Int
    get() = windowMetricsCalculator.computeMaximumWindowMetrics(this).bounds.height()

val Activity.maxWindowHeightWithoutSystemBars: Int
    get() = maxWindowHeight - statusBarHeight - navBarHeight

val Activity.statusBarHeight: Int
    get() = ViewCompat.getRootWindowInsets(window.decorView)
        ?.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars())?.top ?: 0

val Activity.navBarHeight: Int
    get() = ViewCompat.getRootWindowInsets(window.decorView)
        ?.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars())?.bottom ?: 0