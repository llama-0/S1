package com.example.s1

import android.content.Intent
import android.content.res.Resources
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.content_main.*

class GreetingsPresenter(
    private val view: GreetingsContract.View,
    private val model: Model,
    private val resources: Resources
) : GreetingsContract.Presenter {


}