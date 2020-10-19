package com.llama.simplemvp.contract

import com.llama.simplemvp.presenter.BasePresenter
import com.llama.simplemvp.view.BaseView

interface ResponseContract {

    interface View : BaseView {
        fun showMessage(message: String)
    }

    interface Presenter : BasePresenter {
        fun initView()
    }

}

