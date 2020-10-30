package com.llama.simplemvp.data

import android.content.SharedPreferences
import com.llama.simplemvp.R
import com.nhaarman.mockitokotlin2.*
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
    fun `setName if name saved return this name`() {
        model.setName(STR_TEST_NAME)

        verify(prefs).edit()
        verify(editor).putString(eq(PREF_STR_NAME_KEY), eq(STR_TEST_NAME))
        verify(editor).apply()
    }

    @Test
    fun `setName if name is empty return empty string`() {
        model.setName("")

        verify(prefs).edit()
        verify(editor).putString(eq(PREF_STR_NAME_KEY), eq(""))
        verify(editor).apply()
    }

    @Test
    fun `setColor if color saved return this color`() {
        model.setColor(R.color.selectableColorFirst)

        verify(prefs).edit()
        verify(editor).putInt(eq(PREF_INT_COLOR_KEY), eq(R.color.selectableColorFirst))
        verify(editor).apply()
    }

    @Test // broken with anyString(), but why the test with color anyInt() works then ??
    fun `getName if some name saved return this name`() {
        whenever(prefs.getString(eq(PREF_STR_NAME_KEY), anyString()))
            .thenReturn(STR_TEST_NAME)

        val name: String = model.getName()

        Assert.assertThat(name, `is`(STR_TEST_NAME))
    }

    @Test
    fun `getName if nothing saved return empty string`() {
        whenever(prefs.getString(eq(PREF_STR_NAME_KEY), anyString()))
            .thenReturn("")

        val name: String = model.getName()

        Assert.assertThat(name, `is`(""))
    }

    @Test
    fun `getColor if color saved return color`() {
        whenever(prefs.getInt(eq(PREF_INT_COLOR_KEY), anyInt()))
            .thenReturn(R.color.selectableColorFirst)

        val color: Int = model.getColor()

        Assert.assertThat(color, `is`(R.color.selectableColorFirst))
    }

    companion object {
        private const val PREF_INT_COLOR_KEY = "PREF_INT_COLOR_KEY"
        private const val PREF_STR_NAME_KEY = "PREF_STR_NAME_KEY"
        private const val STR_TEST_NAME = "Ana"
    }
}