package com.example.s1

interface GreetingsContract {

    interface View : BaseView<Presenter?> {
        fun showGreetings(message: String)
    }

    interface Presenter : BasePresenter {
    }

}