package com.llama.simplemvp.data

import com.llama.simplemvp.R

enum class SelectableColors(val color: Int) {
    FIRST(R.color.selectableColorFirst),
    SECOND(R.color.selectableColorSecond),
    THIRD(R.color.selectableColorThird)
}

enum class RadioButtonIds(val id: Int) {
    FIRST(R.id.rbColorFirst),
    SECOND(R.id.rbColorSecond),
    THIRD(R.id.rbColorThird)
}