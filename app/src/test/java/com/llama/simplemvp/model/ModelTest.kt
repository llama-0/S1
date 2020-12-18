package com.llama.simplemvp.model

import android.content.SharedPreferences
import com.llama.simplemvp.R
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString

class ModelTest {

    private val prefs: SharedPreferences = mock()
    private val editor: SharedPreferences.Editor = mock()
    private val model: Model = Model(prefs)

    @Before
    fun before() {
        whenever(prefs.edit()).thenReturn(editor)
    }

    @Test
    fun `setName given name when saved to shared prefs then verify string added`() {
        model.setName(STR_TEST_NAME)

        verifyPrefsSaved(PREF_STR_NAME_KEY, STR_TEST_NAME, TYPE_STR)
    }

    @Test
    fun `setName given empty string as name when saved to shared prefs verify string added`() {
        model.setName("")

        verifyPrefsSaved(PREF_STR_NAME_KEY, "", TYPE_STR)
    }

    @Test
    fun `setColor given selectableColorFirst as color when color saved verify int added`() {
        model.setColor(R.color.selectableColorFirst)

        verifyPrefsSaved(PREF_INT_COLOR_KEY, R.color.selectableColorFirst, TYPE_INT)
    }

    @Test
    fun `getName given name saved when getName called check name matches name added`() {
        verifyName(PREF_STR_NAME_KEY, STR_TEST_NAME)
    }

    @Test
    fun `getName given empty string saved when getName called check name matches empty string`() {
        verifyName(PREF_STR_NAME_KEY, "")
    }

    @Test
    fun `getColor given selectableColorFirst saved when getColor called check color matches selectableColorFirst`() {
        val matcher: Int = R.color.selectableColorFirst

        whenever(prefs.getInt(eq(PREF_INT_COLOR_KEY), anyInt()))
            .thenReturn(matcher)

        val actual: Int = model.getColor()

        Assert.assertThat(actual, `is`(matcher))
    }

    private fun <T> verifyPrefsSaved(key: String, value: T, type: String) {
        verify(prefs).edit()
        when (type) {
            TYPE_STR -> verify(editor).putString(eq(key), eq(value.toString()))
            TYPE_INT -> verify(editor).putInt(eq(key), eq(value.toString().toInt()))
        }
        verify(editor).apply()
    }

    private fun verifyName(key: String, matcher: String) {
        whenever(prefs.getString(eq(key), anyString()))
            .thenReturn(matcher)

        val name: String = model.getName()

        Assert.assertThat(name, `is`(matcher))
    }

    companion object {
        private const val PREF_INT_COLOR_KEY = "PREF_INT_COLOR_KEY"
        private const val PREF_STR_NAME_KEY = "PREF_STR_NAME_KEY"
        private const val STR_TEST_NAME = "Ana"
        private const val TYPE_STR = "STR"
        private const val TYPE_INT = "INT"
    }
}