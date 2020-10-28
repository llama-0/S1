package com.llama.simplemvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.llama.simplemvp.R
import com.llama.simplemvp.utils.Navigator

class MainActivity : AppCompatActivity(R.layout.activity_main), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFragment(R.id.main_container, EnterNameFragment())
    }

//    override fun onSupportNavigateUp(): Boolean {
//        supportFragmentManager.popBackStack()
//        return true
//    }

    override fun goToFragment(fragment: Fragment) {
        replaceFragment(R.id.main_container, fragment)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            android.R.id.home -> navigateBack()
//        }
//
//        return super.onOptionsItemSelected(item)
//    }

    override fun navigateBack() {
        supportFragmentManager
            .popBackStack()
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    private fun addFragment(containerId: Int, fragment: Fragment){
        supportFragmentManager.inTransaction { add(containerId, fragment) }
    }


    private fun replaceFragment(containerId: Int, fragment: Fragment) {
        supportFragmentManager.inTransaction{
            replace(containerId, fragment)
            addToBackStack(null)
        }
    }
}