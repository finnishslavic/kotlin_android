package com.slavaware.kotlinandroid.domain.commands

interface Command<T> {
    fun execute(): T
}
