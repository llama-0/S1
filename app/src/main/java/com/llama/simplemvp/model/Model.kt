package com.llama.simplemvp.model

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting

class Model(private val prefs: SharedPreferences) {

    fun setName(name: String) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(PREF_STR_NAME_KEY, name)
        editor.apply()
//        prefs.edit().putString(PREF_STR_NAME_KEY, name).apply()
    }

    fun getName(): String =
        prefs.getString(PREF_STR_NAME_KEY, "def_str") ?: STR_DEFAULT_NAME

    fun setColor(color: Int) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putInt(PREF_INT_COLOR_KEY, color)
        editor.apply()
//        prefs.edit().putInt(PREF_INT_COLOR_KEY, color).apply()
    }

    fun getColor(): Int =
        prefs.getInt(PREF_INT_COLOR_KEY, 0)

    companion object {
//        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val PREF_INT_COLOR_KEY = "PREF_INT_COLOR_KEY"
//        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val PREF_STR_NAME_KEY = "PREF_STR_NAME_KEY"
        private const val STR_DEFAULT_NAME = ""
    }
}