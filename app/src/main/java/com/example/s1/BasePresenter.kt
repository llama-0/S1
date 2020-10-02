package com.example.s1

interface BasePresenter {
    fun start() = println("initialize in interface itself, kotlin does this")
}