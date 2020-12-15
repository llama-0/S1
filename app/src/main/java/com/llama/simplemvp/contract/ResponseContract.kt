package com.llama.simplemvp.contract

interface ResponseContract {

    interface View {
        fun showMessage(message: String)
        fun showTextViewBackgroundColor()
    }

    interface Presenter {
        fun initView()
    }

}

