package com.llama.simplemvp.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.llama.simplemvp.App
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.ResponseContract
import com.llama.simplemvp.presenter.ResponsePresenter
import kotlinx.android.synthetic.main.activity_response.*

class ResponseActivity : AppCompatActivity(), ResponseContract.View {

    var presenter: ResponseContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)

        initPresenter()
        val color = intent.getIntExtra(STR_COLOR, Color.GRAY)
        tvHello.setBackgroundColor(color)
    }

    private fun initPresenter() {
        (application as App).model?.let {
            presenter = ResponsePresenter(this, it, resources)
        }
        presenter?.initView()
    }

    override fun showMessage(message: String) {
        tvHello.text = message
    }

    companion object {
        private const val STR_COLOR = "STR_COLOR"
    }
}