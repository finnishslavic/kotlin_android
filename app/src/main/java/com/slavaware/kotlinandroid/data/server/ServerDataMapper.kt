package com.slavaware.kotlinandroid.data.server

import com.slavaware.kotlinandroid.domain.model.ForecastList
import com.slavaware.kotlinandroid.domain.model.Forecast as ModelForecast

class ServerDataMapper {

    fun convertToDomain(zipCode: Long, forecast: ForecastResult): ForecastList {
        return ForecastList(zipCode, forecast.city.name, forecast.city.country, convertForecastToDomainList(forecast.list))
    }

    private fun convertForecastToDomainList(list: List<Forecast>): List<com.slavaware.kotlinandroid.domain.model.Forecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): com.slavaware.kotlinandroid.domain.model.Forecast = with(forecast) {
        return com.slavaware.kotlinandroid.domain.model.Forecast(dt * 1000, weather[0].description, temp.max.toInt(),
                temp.min.toInt(), generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String {
        return "http://openweathermap.org/img/w/$iconCode.png"
    }

}

