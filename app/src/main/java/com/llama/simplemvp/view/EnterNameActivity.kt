package com.llama.simplemvp.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.llama.simplemvp.App
import com.llama.simplemvp.utils.SimpleTextWatcher
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.EnterNameContract
import com.llama.simplemvp.presenter.EnterNamePresenter
import kotlinx.android.synthetic.main.activity_enter_name.*

class EnterNameActivity : AppCompatActivity(), EnterNameContract.View {

    private var presenter: EnterNameContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_name)

        initPresenter()
        initListeners()
    }

    private fun initPresenter() {
        (application as App).model?.let {
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
            }
            else
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
            activityEnterName,
            message,
            Snackbar.LENGTH_LONG).show()
    }

    override fun showName(name: String) =
        edEnterName.setText(name)

    override fun showResponseActivity(color: Int) {
        val intent = Intent(this, ResponseActivity::class.java)
        intent.putExtra(STR_COLOR, color)
        startActivity(intent)
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

    companion object {
        private const val STR_COLOR = "STR_COLOR"
    }
}