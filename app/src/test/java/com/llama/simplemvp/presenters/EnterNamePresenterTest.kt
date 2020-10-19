package com.llama.simplemvp.presenters

import android.content.res.Resources
import android.graphics.Color
import com.llama.simplemvp.R
import com.llama.simplemvp.contracts.EnterNameContract
import com.llama.simplemvp.models.Model
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EnterNamePresenterTest {

    private val view: EnterNameContract.View = mock()
    private val model: Model = mock()
    private val resources: Resources = mock()
    private val presenter: EnterNamePresenter = EnterNamePresenter(view, model, resources)

    @Test
    fun initView() {
        presenter.initView()
        verify(view).showName(model.getName())
    }

    @Test
    fun `onShowResponseButtonClicked name is empty`() {
        assertNotNull(model.getName()) // null ...
        whenever(model.getName().isEmpty()).thenReturn(true)
        presenter.onShowResponseButtonClicked()
        verify(view).showMessage(resources.getString(R.string.default_message))
    }

    @Test
    fun `onShowResponseButtonClicked name is Not empty`() {
        whenever(model.getName().isEmpty()).thenReturn(false)
        presenter.onShowResponseButtonClicked()
        verify(view).showResponseActivity()
    }

    @Test
    fun `onRadioButtonChecked color RED`() {
        presenter.onRadioButtonChecked(Color.RED)
        verify(model).setColor(Color.RED)
    }

    @Test
    fun `onRadioButtonChecked color BLUE`() {
        presenter.onRadioButtonChecked(Color.BLUE)
        verify(model).setColor(Color.BLUE)
    }

    @Test
    fun getRadioGroupResult() {
        presenter.getRadioGroupResult()
        verify(model).getColor()
    }
}