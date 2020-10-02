package com.example.s1

import MainPresenter
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ContractMain.View {

    private var sharedPreferences: SharedPreferences? = null

    companion object {
        private const val PREFERENCES_TAG = "PREFERENCES_KEY"
        private const val INPUT_KEY = "INPUT_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity","onCreate called")

        sharedPreferences = getSharedPreferences(PREFERENCES_TAG, 0)
        presenter.start()

        button.setOnClickListener {
            showMessage()
        }
    }

    override fun showMessage() { // unused parameter, all code still in Activity
        Snackbar.make(currentFocus?.rootView!!,
            if (editText.text.isNotEmpty()) editText.text else "Please enter your name",
            Snackbar.LENGTH_LONG).show()
    }

    override fun setText(text: String) {
        editText.setText(text)
    }

    override fun saveText(prefs: SharedPreferences) {
        prefs.edit().putString(INPUT_KEY, editText.text.toString()).apply()
    }

    override fun retrieveText(): String? = sharedPreferences!!.getString(INPUT_KEY, null)

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity","onPause called")
        saveText(sharedPreferences!!)
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity","onResume called")
        val str = retrieveText()
        if (str.isNullOrEmpty()) {
            Log.i("MainActivity", "Str is empty or null")
        } else {
            setText(str)
//            presenter.updateText(str) // does nothing
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity","onDestroy called")
    }

    override var presenter: ContractMain.Presenter = MainPresenter(this)
}