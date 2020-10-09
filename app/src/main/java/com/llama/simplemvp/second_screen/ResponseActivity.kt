package com.llama.simplemvp.second_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.llama.simplemvp.App
import com.llama.simplemvp.R
import kotlinx.android.synthetic.main.activity_response.*

class ResponseActivity : AppCompatActivity(), ResponseContract.View {

    var presenter: ResponseContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)

        initPresenter()
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
}