package com.example.s1

import android.util.Log

interface BasePresenter {
    fun start() = Log.d("BasePresenter", "presenter started")
}