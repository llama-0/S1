package com.llama.simplemvp.view

import android.content.res.Resources
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat

fun Resources.getColorCompat(@ColorRes color: Int): Int =
    ResourcesCompat.getColor(this, color, null)