package com.llama.simplemvp.view.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.llama.simplemvp.App
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.EnterNameContract
import com.llama.simplemvp.presenter.EnterNamePresenter
import com.llama.simplemvp.view.RadioButtonColorPerId
import com.llama.simplemvp.view.RadioButtonColorPerId.*
import com.llama.simplemvp.view.SimpleTextWatcher
import com.llama.simplemvp.view.activity.MainActivity
import com.llama.simplemvp.view.getColorCompat
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
                    (presenter as EnterNamePresenter).init()
                }
            }
        }
    }

    private fun initListeners() {
        setRadioGroupListener()
        setTextListener()
        setButtonListener()
    }

    private fun setRadioGroupListener() {
        rgTextViewBackgroundColor.setOnCheckedChangeListener { radioGroup, radioButtonId ->
            val rb: RadioButton = radioGroup.findViewById(radioButtonId)
            val rbIds: RadioButtonColorPerId =
                values().single { it.id == rb.id }
            presenter?.onRadioButtonChecked(rbIds)
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

    override fun enableShowResponseButton() {
        btnShowResponse.isEnabled = true
    }

    override fun setDefaultButtonState() {
        btnShowResponse.isEnabled = false
    }

    override fun showName(name: String) =
        edEnterName.setText(name)

    override fun hideKeyboard() {
        val inputMethodManager: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            view?.windowToken,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
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
            resources.getColorCompat(FIRST.color) ->
                rgTextViewBackgroundColor.check(rbColorFirst.id)
            resources.getColorCompat(SECOND.color) ->
                rgTextViewBackgroundColor.check(rbColorSecond.id)
            resources.getColorCompat(THIRD.color) ->
                rgTextViewBackgroundColor.check(rbColorThird.id)
        }
    }

}