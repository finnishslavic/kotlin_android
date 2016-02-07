package com.slavaware.kotlinandroid.data.server

import com.slavaware.kotlinandroid.data.db.ForecastDb
import com.slavaware.kotlinandroid.domain.datasource.ForecastDataSource
import com.slavaware.kotlinandroid.domain.model.ForecastList

class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(), val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

}
