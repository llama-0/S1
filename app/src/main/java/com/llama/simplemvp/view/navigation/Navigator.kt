package com.llama.simplemvp.view.navigation

import androidx.fragment.app.Fragment

interface Navigator {

    fun goToFragment(fragment: Fragment)
}