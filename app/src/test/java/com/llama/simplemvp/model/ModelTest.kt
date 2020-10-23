package com.llama.simplemvp.model

import android.content.SharedPreferences
import android.graphics.Color
import com.llama.simplemvp.R
import com.nhaarman.mockitokotlin2.*
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ModelTest {

    private val prefs: SharedPreferences = mock()
    private val editor: SharedPreferences.Editor = mock()
    private val model: Model = Model(prefs)

    @Before
    fun before() {
        whenever(prefs.edit()).thenReturn(editor)
    }

    @Test
    fun `setName some name Not empty`() {
        model.setName(STR_TEST_NAME)

        verify(prefs).edit()
        verify(editor).putString(eq(PREF_STR_NAME_KEY), eq(STR_TEST_NAME))
        verify(editor).apply()
    }

    @Test
    fun `setName empty string`() {
        model.setName("")

        verify(prefs).edit()
        verify(editor).putString(eq(PREF_STR_NAME_KEY), eq(""))
        verify(editor).apply()
    }

    @Test
    fun `setColor RED`() {
        model.setColor(Color.RED)

        verify(prefs).edit()
        verify(editor).putInt(eq(PREF_INT_COLOR_KEY), eq(Color.RED))
        verify(editor).apply()
    }

    @Test
    fun `getName some name Not empty`() {
        whenever(prefs.getString(eq(PREF_STR_NAME_KEY), eq(null)))
            .thenReturn(STR_TEST_NAME)

        val name: String = model.getName()
        Assert.assertThat(name, `is`(STR_TEST_NAME))
    }

    @Test
    fun `getName empty string`() {
        model.getName()

        verify(prefs).getString(eq(PREF_STR_NAME_KEY), isNull())
    }

    @Test
    fun `getColor RED`() {
        whenever(prefs.getInt(eq(PREF_INT_COLOR_KEY), eq(R.color.colorCustomGray)))
            .thenReturn(Color.RED)

        val color: Int = model.getColor()
        Assert.assertThat(color, `is`(Color.RED))
    }

    companion object {
        private const val PREF_INT_COLOR_KEY = "PREF_INT_COLOR_KEY"
        private const val PREF_STR_NAME_KEY = "PREF_STR_NAME_KEY"
        private const val STR_TEST_NAME = "Ana"
    }
}