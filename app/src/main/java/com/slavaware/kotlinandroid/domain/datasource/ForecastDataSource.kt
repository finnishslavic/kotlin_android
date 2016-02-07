package com.slavaware.kotlinandroid.domain.datasource

import com.slavaware.kotlinandroid.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}

