package com.llama.simplemvp.contract

import com.llama.simplemvp.data.RadioButtonIds

interface EnterNameContract {

    interface View {
        fun showMessage(message: String)
        fun showName(name: String)
        fun hideKeyboard()
        fun showResponseFragmentWithTextViewBackgroundColor(color: Int)
        fun showCheckedRadioButton(color: Int)
    }

    interface Presenter {
        fun initView()
        fun onShowResponseButtonClicked()
        fun onNameChanged(name: String)
        fun onRadioButtonChecked(rbIds: RadioButtonIds)
    }

}