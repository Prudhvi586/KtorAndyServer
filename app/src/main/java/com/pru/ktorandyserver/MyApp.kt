package com.pru.ktorandyserver

import android.app.Application
import android.content.Context
import com.pru.ktorandyserver.server.ApiServer

private var appContext_: Context? = null
val appContext: Context
    get() = appContext_ ?: throw IllegalStateException(
        "Application context not initialized yet."
    )
private var apiServer_: ApiServer? = null
val apiServer: ApiServer
    get() = apiServer_ ?: throw IllegalStateException(
        "API server not initialized yet."
    )

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext_ = applicationContext
        apiServer_ = ApiServer()
    }
}