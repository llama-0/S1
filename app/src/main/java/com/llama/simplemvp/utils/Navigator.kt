package com.llama.simplemvp.utils

import androidx.fragment.app.Fragment
import com.llama.simplemvp.R
import com.llama.simplemvp.view.ResponseFragment

class Navigator {

    fun navigate(withColor: Int) {
        val responseFragment: ResponseFragment = ResponseFragment.newInstance(withColor)
        Fragment().replaceFragment(R.id.main_container, responseFragment)
    }
}