package com.llama.simplemvp.contract

import com.llama.simplemvp.presenter.BasePresenter
import com.llama.simplemvp.view.BaseView

interface EnterNameContract {

    interface View : BaseView {
        fun showMessage(message: String)
        fun showName(name: String)
        fun hideKeyboard()
        fun showResponseFragmentWithTextViewBackgroundColor(color: Int)
        fun showCheckedRadioButton(color: Int)
    }

    interface Presenter : BasePresenter {
        fun initView()
        fun onShowResponseButtonClicked()
        fun onNameChanged(name: String)
        fun onRadioButtonChecked(color: Int)
        fun getRadioGroupResult(): Int
    }

}