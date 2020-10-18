package com.llama.simplemvp.models

import android.content.SharedPreferences
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ModelTest {

//    private lateinit var prefs: SharedPreferences
//    private lateinit var model: Model
//
//    @Before
//    fun setUp() {
//        prefs = Mockito.mock(SharedPreferences::class.java)
//        model = Model(prefs)
//    }

    private val prefs: SharedPreferences = Mockito.mock(SharedPreferences::class.java)
    private val model: Model = Model(prefs)

    @Test
    fun `setName Ana`() {
        model.setName(STR_NAME_VALUE_ANA)
        val actual: String = prefs.getString(PREF_STR_NAME_KEY, null) ?: STR_NAME_EMPTY
        Assert.assertEquals(STR_NAME_VALUE_ANA, actual)
    }

    @Test
    fun `setName empty string`() {
        model.setName(STR_NAME_EMPTY)
        val actual: String = prefs.getString(PREF_STR_NAME_KEY, null) ?: STR_NAME_EMPTY
        Assert.assertEquals(STR_NAME_EMPTY, actual)
    }

    @Test
    fun `getName Ana`() {
        prefs.edit().putString(PREF_STR_NAME_KEY, STR_NAME_VALUE_ANA).apply()
        val actual: String = model.getName()
        Assert.assertEquals(STR_NAME_VALUE_ANA, actual)
    }

    @Test
    fun `getName empty string`() {
        prefs.edit().putString(PREF_STR_NAME_KEY, STR_NAME_EMPTY).apply()
        val actual: String = model.getName()
        Assert.assertEquals(STR_NAME_EMPTY, actual)
    }

    @Test
    fun setColor() {
    }

    @Test
    fun getColor() {
    }

    companion object {
        private const val PREF_STR_NAME_KEY = "PREF_STR_NAME_KEY"
        private const val STR_NAME_VALUE_ANA = "Ana"
        private const val STR_NAME_EMPTY = ""
    }
}