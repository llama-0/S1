package com.example.s1

import android.content.res.Resources
import android.provider.Settings.Global.getString
import kotlinx.android.synthetic.main.content_main.*

class EnterNamePresenter(
    private val view: EnterNameContract.View,
    private val model: Model
) : EnterNameContract.Presenter {

    override fun saveName(name: String) =
        model.setName(name)

    override fun onViewCreated() =
        view.showName(model.getName())

    override fun onShowNameButtonClicked(name: String) {
        saveName(name)
        val message = if (name.isNotEmpty()) "Hello, $name!" else DEFAULT_MESSAGE
        view.showGreetings(message)
    }

    companion object {
        private const val DEFAULT_MESSAGE = "Please enter your name"
    }
}