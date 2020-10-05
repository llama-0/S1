package com.example.s1

import EnterNamePresenter
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_main.*

class EnterNameActivity : AppCompatActivity(), EnterNameContract.View {

    private var sharedPreferences: SharedPreferences? = null
    private var input: Model? = null
    override var presenter: EnterNameContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(PREFERENCES_TAG, 0)
        input = Model(sharedPreferences)
        presenter = EnterNamePresenter(this)
        presenter?.start()

        btn.setOnClickListener {
            presenter?.onShowNameButtonClicked(input, edEnterName.text.toString())
        }
    }

    override fun showMessage() {
        Snackbar.make(
            findViewById(R.id.content_main),
            if (edEnterName.text.isNotEmpty())
                "Hello, ${edEnterName.text}!"
            else
                getString(R.string.default_message),
            Snackbar.LENGTH_LONG).show()
    }

    override fun setText(text: String) {
        edEnterName.setText(text)
    }

    override fun onResume() {
        super.onResume()
        Log.i("EnterNameActivity","onResume called")
        presenter?.retrieveText(input)?.let { setText(it) }
    }

    companion object {
        private const val PREFERENCES_TAG = "PREFERENCES_KEY"
    }
}