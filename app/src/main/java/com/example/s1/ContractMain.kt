package com.example.s1

import android.content.SharedPreferences

interface ContractMain {

    interface View : BaseView<Presenter> {
        fun showMessage()
        fun setText(text: String)
        fun saveText(prefs: SharedPreferences)
        fun retrieveText(): String?
    }

    interface Presenter : BasePresenter {
    }
}