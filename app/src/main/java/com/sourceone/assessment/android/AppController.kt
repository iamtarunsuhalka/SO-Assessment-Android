package com.sourceone.assessment.android

import android.app.Application
import android.content.Context

class AppController: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        private var instance: Application? = null

        fun getApplicationContext(): Context {
            return instance!!.applicationContext
        }
    }

}
