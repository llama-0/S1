package com.llama.simplemvp.views

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.llama.simplemvp.App
import com.llama.simplemvp.utils.CustomTextWatcher
import com.llama.simplemvp.R
import com.llama.simplemvp.contracts.EnterNameContract
import com.llama.simplemvp.presenters.EnterNamePresenter
import kotlinx.android.synthetic.main.activity_enter_name.*

class EnterNameActivity : AppCompatActivity(), EnterNameContract.View {

    private var presenter: EnterNameContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_name)

        initPresenter()
        initRadioGroupView()
        initListeners()
    }

    private fun initPresenter() {
        (application as App).model?.let {
            presenter = EnterNamePresenter(this, it, resources)
        }
        presenter?.initView()
    }

    private fun initRadioGroupView() {
        val id: Int = when(presenter?.getRadioGroupResult()) {
            Color.RED -> rbColorRed.id
            Color.GREEN -> rbColorGreen.id
            Color.BLUE -> rbColorBlue.id
            else -> -1
        }
        rgTextViewBackgroundColor.check(id)
    }

    private fun initListeners() {
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
        edEnterName.addTextChangedListener(CustomTextWatcher(after = {
            presenter?.onNameChanged(it.toString())
        }))
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
        val color: Int? = presenter?.getRadioGroupResult()
        intent.putExtra(STR_COLOR, color)
        startActivity(intent)
    }

    companion object {
        private const val STR_COLOR = "STR_COLOR"
    }
}