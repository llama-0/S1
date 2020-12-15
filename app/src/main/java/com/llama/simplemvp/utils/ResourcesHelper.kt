package com.llama.simplemvp.utils

import android.content.res.Resources
import androidx.core.content.res.ResourcesCompat

fun getColorFromResources(resources: Resources, color: Int): Int =
    ResourcesCompat.getColor(resources, color, null)