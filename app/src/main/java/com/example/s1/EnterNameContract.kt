package com.example.s1

interface EnterNameContract {

    interface View : BaseView<Presenter?> {
        fun showMessage()
        fun setText(text: String)
    }

    interface Presenter : BasePresenter {
        fun saveText(input: Model?, text: String)
        fun retrieveText(input: Model?): String?
    }
}