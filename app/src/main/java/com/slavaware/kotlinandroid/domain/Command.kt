package com.slavaware.kotlinandroid.domain

public interface Command<T> {
    fun execute(): T
}
