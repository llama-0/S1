package com.example.s1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.content_greetings.*

class GreetingsActivity : AppCompatActivity(), GreetingsContract.View {

    override var presenter: GreetingsContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greetings)

        initPresenter()
    }

    private fun initPresenter() {
        presenter = GreetingsPresenter(this, AppSingleton.model, resources)
        presenter?.start()
        presenter?.onViewCreated()
    }

    override fun showGreetings(message: String) {
        tvGreetings.text = message
    }
}