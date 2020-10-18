package com.llama.simplemvp.utils

import android.text.Editable
import android.text.TextWatcher

class CustomTextWatcher(
    private val after: ((Editable?) -> Unit),
    private val before: ((CharSequence?, Int, Int, Int) -> Unit) = {_, _, _, _ -> },
    private val on: ((CharSequence?, Int, Int, Int) -> Unit) = {_, _, _, _ -> }
) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        before(p0, p1, p2, p3)
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        on(p0, p1, p2, p3)
    }

    override fun afterTextChanged(p0: Editable?) {
        after(p0)
    }
}