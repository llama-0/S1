package com.llama.simplemvp.models

import android.content.SharedPreferences

class Model(private val prefs: SharedPreferences) {

    fun setName(name: String) {
        prefs.edit()?.putString(PREF_STR_NAME_KEY, name)?.apply()
    }

    fun getName(): String =
        prefs.getString(PREF_STR_NAME_KEY, null) ?: STR_DEFAULT_NAME

    fun setColor(color: Int) {
        prefs.edit()?.putInt(PREF_INT_COLOR_KEY, color)?.apply()
    }

    fun getColor(): Int =
        prefs.getInt(PREF_INT_COLOR_KEY, 0)

    companion object {
        private const val PREF_INT_COLOR_KEY = "PREF_INT_COLOR_KEY"
        private const val PREF_STR_NAME_KEY = "PREF_STR_NAME_KEY"
        private const val STR_DEFAULT_NAME = ""
    }
}