package com.example.s1

import android.content.res.Resources
import android.provider.Settings.Global.getString
import kotlinx.android.synthetic.main.content_main.*

class EnterNamePresenter(
    private val view: EnterNameContract.View,
    private val model: Model,
    private val resources: Resources
) : EnterNameContract.Presenter {

    override fun saveName(name: String) =
        model.setName(name)

    override fun onViewCreated() =
        view.showName(model.getName())

    override fun onShowNameButtonClicked(name: String) {
        saveName(name)
        val message =
            if (name.isNotEmpty())
                "Hello, $name!"
            else
                resources.getString(R.string.default_message)
        view.showGreetings(message)
    }
}