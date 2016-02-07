package com.slavaware.kotlinandroid.data.server

import com.google.gson.Gson
import com.slavaware.kotlinandroid.data.server.ForecastResult
import com.slavaware.kotlinandroid.domain.commands.Command
import com.slavaware.kotlinandroid.data.server.ServerDataMapper
import com.slavaware.kotlinandroid.domain.model.ForecastList
import java.net.URL


class ForecastByZipCodeRequest(val zipCode: Long, val gson: Gson = Gson()) {

    companion object {
        private val APP_ID = "f670c5836b940e03068ca297fd339c43"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return gson.fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}


