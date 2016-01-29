package com.slavaware.kotlinandroid.domain

interface Command<T> {
    fun execute(): T
}
