package com.example.s1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_main.*

class EnterNameActivity : AppCompatActivity(), EnterNameContract.View {

    override var presenter: EnterNameContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPresenter()
        initClickListeners()
    }

    private fun initPresenter() {
        presenter = EnterNamePresenter(this, AppSingleton().model, resources)
        presenter?.start()
        presenter?.onViewCreated()
    }

    private fun initClickListeners() {
        btnShowGreetings.setOnClickListener {
            presenter?.onShowNameButtonClicked(edEnterName.text.toString())
        }
    }

    override fun showMessage(message: String) {
        Snackbar.make(
            findViewById(R.id.content_main),
            message,
            Snackbar.LENGTH_LONG).show()
    }

    override fun showName(name: String) =
        edEnterName.setText(name)

}