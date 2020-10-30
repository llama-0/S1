package com.llama.simplemvp.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.llama.simplemvp.App
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.EnterNameContract
import com.llama.simplemvp.data.RadioButtonIds
import com.llama.simplemvp.data.SelectableColors
import com.llama.simplemvp.presenter.EnterNamePresenter
import com.llama.simplemvp.utils.SimpleTextWatcher
import kotlinx.android.synthetic.main.fragment_enter_name.*

class EnterNameFragment : Fragment(R.layout.fragment_enter_name), EnterNameContract.View {

    private var presenter: EnterNameContract.Presenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        initPresenter()
        initListeners()
    }

    private fun initPresenter() {
        activity?.application.let { app ->
            if (app is App) {
                app.model?.let { 
                    presenter = EnterNamePresenter(this, it, resources)
                }
            }
        }
        presenter?.initView()
    }

    private fun initListeners() {
        setRadioGroupListener()
        setTextListener()
        setButtonListener()
    }

    private fun setRadioGroupListener() {
        rgTextViewBackgroundColor.setOnCheckedChangeListener { radioGroup, radioButtonId ->
            val rb = radioGroup.findViewById<RadioButton>(radioButtonId)
            val rbId = RadioButtonIds.values().single { it.id == rb.id }
            presenter?.onRadioButtonChecked(
                when(rbId) {
                    RadioButtonIds.FIRST -> getColorFromResources(SelectableColors.FIRST.color)
                    RadioButtonIds.SECOND -> getColorFromResources(SelectableColors.SECOND.color)
                    RadioButtonIds.THIRD -> getColorFromResources(SelectableColors.THIRD.color)
                }
            )
        }
    }

    private fun setTextListener() {
        edEnterName.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                presenter?.onNameChanged(p0.toString())
            }
        })
        edEnterName.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                presenter?.onShowResponseButtonClicked()
                return@OnKeyListener true
            } else {
                false
            }
        })
    }

    private fun setButtonListener() {
        btnShowResponse.setOnClickListener {
            presenter?.onShowResponseButtonClicked()
        }
    }

    override fun showMessage(message: String) {
        Snackbar.make(
            constraintLayoutEnterName,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun showName(name: String) =
        edEnterName.setText(name)

    override fun hideKeyboard() {
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    override fun showResponseFragmentWithTextViewBackgroundColor(color: Int) {
        val fragment: ResponseFragment = ResponseFragment.newInstance(color)
        activity?.let {
            if (it is MainActivity) {
                it.goToFragment(fragment)
            }
        }
    }

    override fun showCheckedRadioButton(color: Int) {
        when (color) {
            getColorFromResources(SelectableColors.FIRST.color) -> rgTextViewBackgroundColor.check(rbColorFirst.id)
            getColorFromResources(SelectableColors.SECOND.color) -> rgTextViewBackgroundColor.check(rbColorSecond.id)
            getColorFromResources(SelectableColors.THIRD.color) -> rgTextViewBackgroundColor.check(rbColorThird.id)
        }
    }

    private fun getColorFromResources(color: Int): Int =
        ResourcesCompat.getColor(resources, color, null)

}