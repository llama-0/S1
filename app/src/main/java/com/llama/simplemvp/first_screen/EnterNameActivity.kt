package com.llama.simplemvp.first_screen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.llama.simplemvp.App
import com.llama.simplemvp.R
import com.llama.simplemvp.second_screen.ResponseActivity
import kotlinx.android.synthetic.main.activity_enter_name.*

class EnterNameActivity : AppCompatActivity(), EnterNameContract.View {

    var presenter: EnterNameContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_name)

        initPresenter()
        checkRadioButton()
        initListeners()
    }

    private fun initPresenter() {
        (application as App).model?.let {
            presenter = EnterNamePresenter(this, it, resources)
        }
        presenter?.initView()
    }

    private fun checkRadioButton() {
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

    private fun initListeners() {
        edEnterName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

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

    override fun showResponseActivity() {
        val intent = Intent(this, ResponseActivity::class.java)
        intent.putExtra(STR_COLOR, presenter?.putRadioGroupResult())
        startActivity(intent)
    }

    companion object {
        private const val STR_COLOR = "STR_COLOR"
    }

}