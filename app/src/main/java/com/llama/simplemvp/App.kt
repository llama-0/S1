package com.llama.simplemvp

import android.app.Application
import android.content.SharedPreferences
import com.llama.simplemvp.model.Model

class App : Application() {

    internal var model: Model? = null

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences: SharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE)
        model = Model(sharedPreferences)
    }

    companion object {
        private const val PREFERENCES = "app_preferences"
    }
}