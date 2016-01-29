package com.slavaware.kotlinandroid.domain

import com.slavaware.kotlinandroid.data.Forecast
import com.slavaware.kotlinandroid.data.ForecastResult
import com.slavaware.kotlinandroid.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import com.slavaware.kotlinandroid.domain.model.Forecast as ModelForecast

public class ForecastDataMapper {

    public fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastToDomainList(forecast.list))
    }

    private fun convertForecastToDomainList(list: List<Forecast>): List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt), forecast.weather[0].description, forecast.temp.max.toInt(),
                forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String {
        return "http://openweathermap.org/img/w/$iconCode.png"
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000);
    }
}

