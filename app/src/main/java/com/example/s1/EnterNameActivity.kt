package com.example.s1

import android.content.SharedPreferences
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
        val sharedPreferences = getSharedPreferences(PREFERENCES_TAG, 0)
        val model = Model(sharedPreferences)
        presenter = EnterNamePresenter(this, model)
        presenter?.start()
        presenter?.retrieveName()
    }

    private fun initClickListeners() {
        btn.setOnClickListener {
            presenter?.onShowNameButtonClicked(edEnterName.text.toString())
        }
    }

    override fun showGreetings() {
        Snackbar.make(
            findViewById(R.id.content_main),
            if (edEnterName.text.isNotEmpty())
                "Hello, ${edEnterName.text}!"
            else
                getString(R.string.default_message),
            Snackbar.LENGTH_LONG).show()
    }

    override fun showName(name: String) {
        edEnterName.setText(name)
    }

    companion object {
        private const val PREFERENCES_TAG = "PREFERENCES_KEY"
    }
}