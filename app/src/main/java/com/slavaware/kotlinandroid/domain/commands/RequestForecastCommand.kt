package com.slavaware.kotlinandroid.domain.commands

import com.slavaware.kotlinandroid.domain.datasource.ForecastProvider
import com.slavaware.kotlinandroid.domain.model.ForecastList


class RequestForecastCommand(
        private val zipCode: Long,
        val forecastProvider: ForecastProvider = ForecastProvider()) : Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }
}
