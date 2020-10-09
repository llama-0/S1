package com.llama.simplemvp.base

import android.content.SharedPreferences

class Model(private val prefs: SharedPreferences) {

    fun setName(name: String) {
        prefs.edit()?.putString(PREF_STR_NAME_KEY, name)?.apply()
    }

    fun getName(): String =
        prefs.getString(PREF_STR_NAME_KEY, null) ?: STR_DEFAULT_NAME

    companion object {
        private const val PREF_STR_NAME_KEY = "PREF_STR_NAME_KEY"
        private const val STR_DEFAULT_NAME = ""
    }
}