package com.example.s1

class EnterNamePresenter(
    private val view: EnterNameContract.View,
    private val model: Model
) : EnterNameContract.Presenter {

    override fun saveName(name: String) {
        model.setName(name)
    }

    override fun retrieveName() = view.showName(model.getName())

    override fun onShowNameButtonClicked(name: String) {
        saveName(name)
        view.showGreetings()
    }
}