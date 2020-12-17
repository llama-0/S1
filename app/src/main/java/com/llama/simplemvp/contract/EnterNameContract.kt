package com.llama.simplemvp.contract

import com.llama.simplemvp.view.RadioButtonColorPerId

interface EnterNameContract {

    interface View {
        fun showMessage(message: String)
        fun showName(name: String)
        fun hideKeyboard()
        fun showResponseFragmentWithTextViewBackgroundColor(color: Int)
        fun showCheckedRadioButton(color: Int)
    }

    interface Presenter {
        fun init()
        fun onShowResponseButtonClicked()
        fun onNameChanged(name: String)
        fun onRadioButtonChecked(rbIds: RadioButtonColorPerId)
    }

}