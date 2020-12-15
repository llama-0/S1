package com.llama.simplemvp.presenter

import android.content.res.Resources
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.EnterNameContract
import com.llama.simplemvp.data.Model
import com.llama.simplemvp.data.RadioButtonIds
import com.llama.simplemvp.data.SelectableColors
import com.llama.simplemvp.utils.getColorFromResources

class EnterNamePresenter(
    private val view: EnterNameContract.View,
    private val model: Model,
    private val resources: Resources
) : EnterNameContract.Presenter {

    private fun saveName(name: String) =
        model.setName(name)

    override fun initView() {
        view.showName(model.getName())
        view.showCheckedRadioButton(model.getColor())
    }

    override fun onShowResponseButtonClicked() {
        if (model.getName().isEmpty()) {
            view.hideKeyboard()
            view.showMessage(resources.getString(R.string.default_message))
        } else {
            view.showResponseFragmentWithTextViewBackgroundColor(model.getColor())
        }
    }

    override fun onNameChanged(name: String) {
        if (name != model.getName()) {
            saveName(name)
        }
    }

    override fun onRadioButtonChecked(rbIds: RadioButtonIds) {
        val color: Int = getRadioButtonColor(rbIds)
        model.setColor(color)
    }

    private fun getRadioButtonColor(rbIds: RadioButtonIds): Int = when (rbIds) {
        RadioButtonIds.FIRST -> getColorFromResources(resources, SelectableColors.FIRST.color)
        RadioButtonIds.SECOND -> getColorFromResources(resources, SelectableColors.SECOND.color)
        RadioButtonIds.THIRD -> getColorFromResources(resources, SelectableColors.THIRD.color)
    }
}