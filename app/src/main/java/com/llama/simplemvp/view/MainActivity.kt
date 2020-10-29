package com.llama.simplemvp.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.llama.simplemvp.R
import com.llama.simplemvp.utils.Navigator

class MainActivity : AppCompatActivity(R.layout.activity_main), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFragment(EnterNameFragment())
    }

    override fun goToFragment(fragment: Fragment) {
        replaceFragment(fragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> supportFragmentManager.popBackStack()
        }

        return super.onOptionsItemSelected(item)
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    private fun addFragment(fragment: Fragment){
        supportFragmentManager.inTransaction { add(R.id.main_container, fragment) }
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction{
            replace(R.id.main_container, fragment)
            addToBackStack(null)
        }
    }
}