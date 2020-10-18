package com.llama.simplemvp.contracts

import com.llama.simplemvp.presenters.BasePresenter
import com.llama.simplemvp.views.BaseView

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
        fun onRadioButtonChecked(color: Int)
        fun putRadioGroupResult(): Int
    }

}