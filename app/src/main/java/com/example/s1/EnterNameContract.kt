package com.example.s1

interface EnterNameContract {

    interface View : BaseView<Presenter?> {
        fun showMessage()
        fun showText(text: String)
    }

    interface Presenter : BasePresenter {
        fun onShowNameButtonClicked(text: String)
        fun retrieveText(): String?
        fun saveText(text: String)
    }
}