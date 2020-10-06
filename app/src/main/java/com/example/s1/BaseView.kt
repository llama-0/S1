package com.example.s1

/**
 * BaseView has generic type T for future scalability.
 */
interface BaseView<T> {
    var presenter: T
}