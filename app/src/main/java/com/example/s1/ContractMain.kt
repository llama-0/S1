package com.example.s1

interface ContractMain {

    interface View : BaseView<Presenter> {
        fun showMessage(message: String)
        fun setText(text: String)
    }

    interface Presenter : BasePresenter {
        fun updateText(text: String)
    }
}