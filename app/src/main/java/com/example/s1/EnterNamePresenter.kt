package com.example.s1

import android.content.res.Resources
import android.util.Log

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
        if (name.isEmpty()) {
            view.showMessage(resources.getString(R.string.default_message))
        } else {
            Log.i("EnterNamePresenter", "open second activity or show it")
        }
    }
}