package com.llama.simplemvp.contracts

import com.llama.simplemvp.presenters.BasePresenter
import com.llama.simplemvp.views.BaseView

interface ResponseContract {

    interface View : BaseView {
        fun showMessage(message: String)
    }

    interface Presenter : BasePresenter {
        fun initView()
    }

}

