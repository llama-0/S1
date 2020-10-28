package com.llama.simplemvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.llama.simplemvp.R
import com.llama.simplemvp.utils.Navigator
import com.llama.simplemvp.utils.addFragment
import com.llama.simplemvp.utils.replaceFragment

class MainActivity : AppCompatActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(R.id.main_container, EnterNameFragment())
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }

    override fun goToFragment(fragment: Fragment) {
        replaceFragment(R.id.main_container, fragment)
    }
}