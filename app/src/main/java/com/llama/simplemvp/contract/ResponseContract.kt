package com.llama.simplemvp.contract

interface ResponseContract {

    interface View {
        fun showMessage(message: String)
        fun showTextViewBackgroundColor(color: Int)
    }

    interface Presenter {
        fun init(color: Int)
    }

}

