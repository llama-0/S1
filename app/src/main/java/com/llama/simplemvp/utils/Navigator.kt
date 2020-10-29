package com.llama.simplemvp.utils

import androidx.fragment.app.Fragment

interface Navigator {

    fun goToFragment(fragment: Fragment)
}