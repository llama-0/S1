package com.llama.simplemvp.presenters

import android.content.res.Resources
import com.llama.simplemvp.R
import com.llama.simplemvp.contracts.ResponseContract
import com.llama.simplemvp.models.Model
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ResponsePresenterTest {

    private val view: ResponseContract.View = mock()
    private val model: Model = mock()
    private val resources: Resources = mock()
    private val presenter: ResponsePresenter = ResponsePresenter(view, model, resources)

    @Test
    fun initView() {
        presenter.initView()
        verify(view).showMessage(resources.getString(R.string.hello, model.getName()))
    }
}