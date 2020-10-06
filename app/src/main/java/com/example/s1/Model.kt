package com.example.s1

import android.content.SharedPreferences

data class Model(val prefs: SharedPreferences?) {

    fun setName(name: String) {
        prefs?.edit()?.putString(INPUT_KEY, name)?.apply()
    }

    fun getName() = prefs?.getString(INPUT_KEY, null) ?: "default_name"

    companion object {
        private const val INPUT_KEY = "INPUT_KEY"
    }
}