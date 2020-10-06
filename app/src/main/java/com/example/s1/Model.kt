package com.example.s1

import android.content.SharedPreferences

data class Model(val prefs: SharedPreferences) {

    fun setName(name: String) =
        prefs.edit()?.putString(NAME_KEY, name)?.apply()

    fun getName() =
        prefs.getString(NAME_KEY, null) ?: DEFAULT_NAME

    companion object {
        private const val NAME_KEY = "NAME_KEY"
        private const val DEFAULT_NAME = "default_name"
    }
}