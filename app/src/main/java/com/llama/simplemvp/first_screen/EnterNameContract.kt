package com.llama.simplemvp.first_screen

import com.llama.simplemvp.base.BasePresenter
import com.llama.simplemvp.base.BaseView

interface EnterNameContract {

    interface View : BaseView {
        fun showMessage(message: String)
        fun showName(name: String)
        fun showResponseActivity()
    }

    interface Presenter : BasePresenter {
        fun initView()
        fun onShowResponseButtonClicked()
        fun onNameChanged(name: String)
    }

}