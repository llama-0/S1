package com.example.s1

import android.content.res.Resources

class GreetingsPresenter(
    private val view: GreetingsContract.View,
    private val model: Model,
    private val resources: Resources
) : GreetingsContract.Presenter {

    private fun composeMessage() =
        resources.getString(R.string.hello) + ", " + model.getName() + "!"

    override fun onViewCreated() {
        view.showGreetings(composeMessage())
    }
}