package com.llama.simplemvp.presenter

import android.content.res.Resources
import android.graphics.Color
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.EnterNameContract
import com.llama.simplemvp.model.Model
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
    fun initView() {
        presenter.initView()

        verify(view).showName(model.getName())
    }

    @Test
    fun `onShowResponseButtonClicked name is empty`() {
        whenever(model.getName()).thenReturn("")
        whenever(resources.getString(R.string.default_message))
            .thenReturn("default_message")

        presenter.onShowResponseButtonClicked()

        assert(model.getName().isEmpty())
        verify(view).showMessage(resources.getString(R.string.default_message))
    }

    @Test
    fun `onShowResponseButtonClicked name is Not empty`() {
        whenever(model.getName()).thenReturn("test")

        presenter.onShowResponseButtonClicked()

        assert(model.getName().isNotEmpty())
        verify(view).showResponseFragmentWithTextViewBackgroundColor(model.getColor())
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