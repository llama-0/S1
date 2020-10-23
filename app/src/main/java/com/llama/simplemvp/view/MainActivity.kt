package com.llama.simplemvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.llama.simplemvp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.activity_main_frame, EnterNameFragment())
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }
}