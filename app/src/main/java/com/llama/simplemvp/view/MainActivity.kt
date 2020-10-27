package com.llama.simplemvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.llama.simplemvp.R
import com.llama.simplemvp.utils.addFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(R.id.main_container, EnterNameFragment())
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }
}