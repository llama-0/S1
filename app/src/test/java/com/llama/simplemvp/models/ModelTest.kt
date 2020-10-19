package com.llama.simplemvp.models

import android.content.Context
import android.content.SharedPreferences
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ModelTest {

    private val prefsMocked: SharedPreferences = mock()
    private val model: Model = Model(prefsMocked)

    @Before
    fun before() {
//        verify(prefsMocked).getString(PREF_STR_NAME_KEY, null)
        whenever(model.setName(STR_NAME_VALUE_ANA)).thenReturn(any())
        Assert.assertNotNull(model)
    }

    @Test
    fun `setName Ana`() {
        model.setName(STR_NAME_VALUE_ANA)
        val actual: String = prefsMocked.getString(PREF_STR_NAME_KEY, null) ?: STR_NAME_EMPTY
        Assert.assertEquals(STR_NAME_VALUE_ANA, actual)
    }

//    @Test
//    fun `setName empty string`() {
//        model.setName(STR_NAME_EMPTY)
//        val actual: String = prefs.getString(PREF_STR_NAME_KEY, null) ?: STR_NAME_EMPTY
//        Assert.assertEquals(STR_NAME_EMPTY, actual)
//    }
//
    @Test
    fun `getName Ana`() {
        prefsMocked.edit().putString(PREF_STR_NAME_KEY, STR_NAME_VALUE_ANA).apply()
        val actual: String = model.getName()
        Assert.assertEquals(STR_NAME_VALUE_ANA, actual)
    }
//
//    @Test
//    fun `getName empty string`() {
//        prefs.edit().putString(PREF_STR_NAME_KEY, STR_NAME_EMPTY).apply()
//        val actual: String = model.getName()
//        Assert.assertEquals(STR_NAME_EMPTY, actual)
//    }
//
//    @Test
//    fun setColor() {
//    }
//
//    @Test
//    fun getColor() {
//    }

    companion object {
        private const val PREF_STR_NAME_KEY = "PREF_STR_NAME_KEY"
        private const val STR_NAME_VALUE_ANA = "Ana"
        private const val STR_NAME_EMPTY = ""
    }
}