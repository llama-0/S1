package com.example.s1

import android.app.Application

class AppSingleton : Application() {

    lateinit var model: Model

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences(PREFERENCES_TAG, 0)
        model = Model(sharedPreferences)
    }

    companion object {
        private const val PREFERENCES_TAG = "PREFERENCES_KEY"
    }
}