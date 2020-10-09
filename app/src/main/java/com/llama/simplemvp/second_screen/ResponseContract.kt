package com.llama.simplemvp.second_screen

import com.llama.simplemvp.base.BasePresenter
import com.llama.simplemvp.base.BaseView

interface ResponseContract {

    interface View : BaseView {
        fun showMessage(message: String)
    }

    interface Presenter : BasePresenter {
        fun initView()
    }

}

