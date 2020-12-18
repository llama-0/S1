package com.llama.simplemvp.contract

import androidx.annotation.ColorInt

interface ResponseContract {

    interface View {
        fun showMessage(message: String)
        fun showTextViewBackgroundColor(@ColorInt color: Int)
    }

    interface Presenter {
        fun init(@ColorInt color: Int)
    }

}

