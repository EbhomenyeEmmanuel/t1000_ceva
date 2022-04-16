package com.example.t1000_ceva.data.api.interceptors

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger {
    val TAG = this::class.java.simpleName

    override fun log(message: String) {
        Log.i(TAG, message)
    }
}