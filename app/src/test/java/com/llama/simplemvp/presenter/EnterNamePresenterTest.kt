package com.llama.simplemvp.presenter

import android.content.res.Resources
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.EnterNameContract
import com.llama.simplemvp.model.Model
import com.llama.simplemvp.view.model.RadioButtonColorPerId
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Test

class EnterNamePresenterTest {

    private val view: EnterNameContract.View = mock()
    private val model: Model = mock()
    private val resources: Resources = mock()
    private val presenter: EnterNamePresenter = EnterNamePresenter(view, model, resources)
    private val rbColorPerId: RadioButtonColorPerId = mock()

    @Test
    fun `init given name when initView called check test name is present`() {
        val expectedName = "test name"
        val expectedColor: Int = R.color.selectableColorFirst

        whenever(model.getName()).thenReturn(expectedName)
        whenever(model.getColor()).thenReturn(expectedColor)

        presenter.init()

        verify(view).showName(expectedName)
        verify(view).showCheckedRadioButton(expectedColor)
    }

    @Test
    fun `onShowResponseButtonClicked given getName() returns empty string when button clicked should show default message`() {
        val expectedInput = ""
        val expectedOutput = "default_message"

        whenever(model.getName()).thenReturn(expectedInput)
        whenever(resources.getString(R.string.default_message))
            .thenReturn(expectedOutput)

        presenter.onShowResponseButtonClicked()

        verify(view).hideKeyboard()
        verify(view).showMessage(expectedOutput)
    }

    @Test
    fun `onShowResponseButtonClicked given getName() returns name when button clicked should show target fragment with color`() {
        val expectedInput = "test"
        val expectedOutput: Int = R.color.selectableColorFirst

        whenever(model.getName()).thenReturn(expectedInput)
        whenever(model.getColor()).thenReturn(expectedOutput)

        presenter.onShowResponseButtonClicked()

        verify(view).showResponseFragmentWithTextViewBackgroundColor(expectedOutput)
    }

    @Test
    fun `onRadioButtonChecked given selectableColorFirst when button clicked check color is set`() {
        val expected: Int = R.color.selectableColorFirst

        whenever(rbColorPerId.color).thenReturn(expected)

        presenter.onRadioButtonChecked(rbColorPerId)

        verify(model).setColor(expected)
    }
}