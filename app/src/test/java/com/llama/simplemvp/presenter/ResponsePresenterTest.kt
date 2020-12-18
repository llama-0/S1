package com.llama.simplemvp.presenter

import android.content.res.Resources
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.ResponseContract
import com.llama.simplemvp.model.Model
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

class ResponsePresenterTest {

    private val view: ResponseContract.View = mock()
    private val model: Model = mock()
    private val resources: Resources = mock()
    private val presenter: ResponsePresenter = ResponsePresenter(view, model, resources)

    @Test
    fun `init given name and color when initView called show Hello, test name!`() {
        val expectedMessage = "Hello, $TEST_STR!"
        val expectedColor: Int = R.color.selectableColorFirst

        whenever(model.getName()).thenReturn(TEST_STR)
        whenever(resources.getString(R.string.hello, model.getName()))
            .thenReturn(expectedMessage)

        whenever(model.getColor()).thenReturn(expectedColor)

        presenter.init(expectedColor)

        verify(view).showMessage(expectedMessage)
        verify(view).showTextViewBackgroundColor(expectedColor)
    }

    companion object {
        private const val TEST_STR = "test name"
    }
}