package com.llama.simplemvp.presenter

import android.content.res.Resources
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.ResponseContract
import com.llama.simplemvp.model.Model
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

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