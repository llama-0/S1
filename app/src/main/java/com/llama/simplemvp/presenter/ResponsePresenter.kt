package com.llama.simplemvp.presenter

import android.content.res.Resources
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.ResponseContract
import com.llama.simplemvp.model.Model

class ResponsePresenter(
    private val view: ResponseContract.View,
    private val model: Model,
    private val resources: Resources
) : ResponseContract.Presenter {

    private fun composeMessage(): String =
        resources.getString(R.string.hello, model.getName())

    override fun init(color: Int) {
        view.showMessage(composeMessage())
        view.showTextViewBackgroundColor(color)
    }
}