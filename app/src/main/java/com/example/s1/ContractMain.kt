package com.example.s1

import android.content.SharedPreferences

interface ContractMain {

    interface View : BaseView<Presenter> {
        fun showMessage()
        fun setText(text: String)
    }

    interface Presenter : BasePresenter {
        fun saveText(prefs: SharedPreferences, text: String)
        fun retrieveText(prefs: SharedPreferences): String?
    }
}