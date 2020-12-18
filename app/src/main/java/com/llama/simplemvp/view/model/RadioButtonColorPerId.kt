package com.llama.simplemvp.view.model

import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import com.llama.simplemvp.R

enum class RadioButtonColorPerId(@ColorRes val color: Int, @IdRes val id: Int) {
    FIRST(R.color.selectableColorFirst, R.id.rbColorFirst),
    SECOND(R.color.selectableColorSecond, R.id.rbColorSecond),
    THIRD(R.color.selectableColorThird, R.id.rbColorThird)
}