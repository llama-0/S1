package com.llama.simplemvp.view

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.llama.simplemvp.App
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.EnterNameContract
import com.llama.simplemvp.presenter.EnterNamePresenter
import com.llama.simplemvp.utils.SimpleTextWatcher
import kotlinx.android.synthetic.main.fragment_enter_name.*

class EnterNameFragment : Fragment(), EnterNameContract.View {

    private var presenter: EnterNameContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_enter_name, container, false)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresenter()
        initListeners()
    }

    private fun initPresenter() {
        (activity?.application as App).model?.let {
            presenter = EnterNamePresenter(this, it, resources)
        }
        presenter?.initView()
    }

    private fun initListeners() {
        rgListener()
        edListener()
        btnListener()
    }

    private fun rgListener() {
        rgTextViewBackgroundColor.setOnCheckedChangeListener { radioGroup, radioButtonId ->
            presenter?.onRadioButtonChecked(
                when (radioGroup.findViewById<RadioButton>(radioButtonId)) {
                    rbColorRed -> Color.RED
                    rbColorGreen -> Color.GREEN
                    rbColorBlue -> Color.BLUE
                    else -> Color.GRAY
                }
            )
        }
    }

    private fun edListener() {
        edEnterName.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                presenter?.onNameChanged(p0.toString())
            }
        })
        edEnterName.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                presenter?.onShowResponseButtonClicked()
                return@OnKeyListener true
            } else
                false
        })
    }

    private fun btnListener() {
        btnShowResponse.setOnClickListener {
            presenter?.onShowResponseButtonClicked()
        }
    }

    override fun showMessage(message: String) {
        Snackbar.make(
            fragmentEnterName,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showName(name: String) =
        edEnterName.setText(name)

    override fun showResponseFragment(color: Int) {
        val fragment: ResponseFragment = ResponseFragment.newInstance(color)
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.main_container , fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun showCheckedRadioButton(color: Int) {
        rgTextViewBackgroundColor.check(
            when (color) {
                Color.RED -> rbColorRed.id
                Color.GREEN -> rbColorGreen.id
                Color.BLUE -> rbColorBlue.id
                else -> -1
            }
        )
    }

}