package com.llama.simplemvp.data

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.core.content.res.ResourcesCompat
import com.llama.simplemvp.R

class Model(
    private val prefs: SharedPreferences
//    private val resources: Resources
) {

    fun setName(name: String) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(PREF_STR_NAME_KEY, name)
        editor.apply()
    }

    fun getName(): String =
        prefs.getString(PREF_STR_NAME_KEY, null) ?: STR_DEFAULT_NAME

    fun setColor(color: Int) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putInt(PREF_INT_COLOR_KEY, color)
        editor.apply()
    }

    fun getColor(): Int =
        prefs.getInt(PREF_INT_COLOR_KEY, R.color.colorTextViewBackgroundDefault)

//    fun getColorFromResources(): Int =
//        ResourcesCompat.getColor(resources, getColor(), null)

    companion object {
        private const val PREF_INT_COLOR_KEY = "PREF_INT_COLOR_KEY"
        private const val PREF_STR_NAME_KEY = "PREF_STR_NAME_KEY"
        private const val STR_DEFAULT_NAME = ""
    }
}