package com.slavaware.kotlinandroid.extensions

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object DelegatesExt {
    fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()
}

private class NotNullSingleValueVar<T>() : ReadWriteProperty<Any?, T> {
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }

    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} not initialized")
    }

}
