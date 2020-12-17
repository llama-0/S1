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
    fun `setName given test name when saved to shared prefs then verify string added`() {
        model.setName(STR_TEST_NAME)

        verify(prefs).edit()
        verify(editor).putString(eq(PREF_STR_NAME_KEY), eq(STR_TEST_NAME))
        verify(editor).apply()
    }

    @Test
    fun `setName given empty string as name when saved to shared prefs verify string added`() {
        model.setName("")

        verify(prefs).edit()
        verify(editor).putString(eq(PREF_STR_NAME_KEY), eq(""))
        verify(editor).apply()
    }

    @Test
    fun `setColor given selectableColorFirst as color when color saved verify int added`() {
        model.setColor(R.color.selectableColorFirst)

        verify(prefs).edit()
        verify(editor).putInt(eq(PREF_INT_COLOR_KEY), eq(R.color.selectableColorFirst))
        verify(editor).apply()
    }

    @Test
    fun `getName given test name saved when getName called check name matches test name`() {
        whenever(prefs.getString(eq(PREF_STR_NAME_KEY), anyString()))
            .thenReturn(STR_TEST_NAME)

        val name: String = model.getName()

        Assert.assertThat(name, `is`(STR_TEST_NAME))
    }

    @Test
    fun `getName given empty string saved when getName called check name matches empty string`() {
        whenever(prefs.getString(eq(PREF_STR_NAME_KEY), anyString()))
            .thenReturn("")

        val name: String = model.getName()

        Assert.assertThat(name, `is`(""))
    }

    @Test
    fun `getColor given selectableColorFirst saved when getColor called check color matches selectableColorFirst`() {
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