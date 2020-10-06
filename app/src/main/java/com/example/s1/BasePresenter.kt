package com.example.s1

import android.util.Log

/**
 * BasePresenter is a part of an architecture skeleton.
 * May come useful for future scalability, now redundant.
 */
interface BasePresenter {
    fun start() =
        Log.d("BasePresenter", "presenter started")
}