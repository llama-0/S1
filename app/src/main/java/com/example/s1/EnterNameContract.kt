package com.example.s1

interface EnterNameContract {

    interface View : BaseView<Presenter?> {
        fun showMessage(message: String)
        fun showName(name: String)
        fun showGreetingsActivity()
    }

    interface Presenter : BasePresenter {
        fun saveName(name: String): Unit?
        fun onViewCreated()
        fun onShowNameButtonClicked(name: String)
    }

}