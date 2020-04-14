package com.cowen.copower.commons

import android.util.Log

object Logger {

    private const val TAG = "Copower"

    /**
     * dt: Debug with Thread details.
     * Print current thread name plus given value.
     */
    fun dt(value: String) {
        Log.d(TAG, "Thread Name: ${Thread.currentThread().name} - $value")
    }
}