package com.llama.simplemvp.presenter

import android.content.res.Resources
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.EnterNameContract
import com.llama.simplemvp.data.Model
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

class EnterNamePresenterTest {

    private val view: EnterNameContract.View = mock()
    private val model: Model = mock()
    private val resources: Resources = mock()
    private val presenter: EnterNamePresenter = EnterNamePresenter(view, model, resources)

    @Test
    fun `initView given test name when initView called check test name is present`() {
        whenever(model.getName()).thenReturn("test name")

        presenter.initView()

        verify(view).showName(model.getName())
    }

    @Test
    fun `onShowResponseButtonClicked given getName() returns empty string when button clicked should show default message`() {
        whenever(model.getName()).thenReturn("")
        whenever(resources.getString(R.string.default_message))
            .thenReturn("default_message")

        presenter.onShowResponseButtonClicked()

        assert(model.getName().isEmpty())
        verify(view).showMessage(resources.getString(R.string.default_message))
    }

    @Test
    fun `onShowResponseButtonClicked given getName() returns test name when button clicked should show target fragment with color`() {
        whenever(model.getName()).thenReturn("test")
        whenever(model.getColor()).thenReturn(R.color.selectableColorFirst)

        presenter.onShowResponseButtonClicked()

        assert(model.getName().isNotEmpty())
        verify(view).showResponseFragmentWithTextViewBackgroundColor(model.getColor())
    }

    @Test
    fun `onRadioButtonChecked given selectableColorFirst when button clicked check color is set`() {
        presenter.onRadioButtonChecked(R.color.selectableColorFirst)

        verify(model).setColor(R.color.selectableColorFirst)
    }
}