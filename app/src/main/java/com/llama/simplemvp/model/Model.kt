package com.llama.simplemvp.model

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting

class Model(private val prefs: SharedPreferences) {

    fun setName(name: String) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(PREF_STR_NAME_KEY, name)
        editor.apply()
    }

    fun getName(): String {
        val str: String? = prefs.getString(PREF_STR_NAME_KEY, null)
        return str ?: STR_DEFAULT_NAME
    }

    fun setColor(color: Int) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putInt(PREF_INT_COLOR_KEY, color)
        editor.apply()
    }

    fun getColor(): Int =
        prefs.getInt(PREF_INT_COLOR_KEY, 0)

    companion object {
//        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val PREF_INT_COLOR_KEY = "PREF_INT_COLOR_KEY"
//        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val PREF_STR_NAME_KEY = "PREF_STR_NAME_KEY"
//        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val STR_DEFAULT_NAME = ""
    }
}