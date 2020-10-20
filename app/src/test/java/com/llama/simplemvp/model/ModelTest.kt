package com.llama.simplemvp.model

import android.content.SharedPreferences
import android.graphics.Color
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

class ModelTest {

    private val prefsMocked: SharedPreferences = mock()
    private val mockEditor: SharedPreferences.Editor = mock()
    private val model: Model = Model(prefsMocked)

    @Before
    fun before() {
        whenever(prefsMocked.edit()).thenReturn(mockEditor)
    }

    @Test
    fun `setName Ana`() {
        model.setName("Ana")

        verify(prefsMocked).edit()
        verify(mockEditor).putString(eq(Model.PREF_STR_NAME_KEY), eq("Ana"))
        verify(mockEditor).apply()
//        verify(prefsMocked).edit().putString(eq(Model.PREF_STR_NAME_KEY), eq("qq")).apply()
    }

    @Test
    fun `setName empty string`() {
        model.setName("")

        verify(prefsMocked).edit()
        verify(mockEditor).putString(eq(Model.PREF_STR_NAME_KEY), eq(""))
        verify(mockEditor).apply()
    }

    @Test
    fun `getName Ana`() {
        assert(false)// todo
    }

    @Test
    fun `getName empty string`() {
        model.getName()

        verify(prefsMocked).getString(eq(Model.PREF_STR_NAME_KEY), isNull())
    }

    @Test
    fun `setColor RED`() {
        model.setColor(Color.RED)

        verify(prefsMocked).edit()
        verify(mockEditor).putInt(eq(Model.PREF_INT_COLOR_KEY), eq(Color.RED))
        verify(mockEditor).apply()
    }

    @Test
    fun `getColor RED`() {
        assert(false) // todo
    }
}