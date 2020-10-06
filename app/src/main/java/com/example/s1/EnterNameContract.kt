package com.example.s1

interface EnterNameContract {

    interface View : BaseView<Presenter?> {
        fun showGreetings()
        fun showName(name: String)
    }

    interface Presenter : BasePresenter {
        fun saveName(name: String)
        fun retrieveName()
        fun onShowNameButtonClicked(name: String)
    }
}