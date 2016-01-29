package com.slavaware.kotlinandroid.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import com.slavaware.kotlinandroid.R
import com.slavaware.kotlinandroid.data.ForecastResult
import com.slavaware.kotlinandroid.domain.Command
import com.slavaware.kotlinandroid.domain.ForecastDataMapper
import com.slavaware.kotlinandroid.domain.model.ForecastList
import com.slavaware.kotlinandroid.ui.adapters.ForecastListAdapter
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList: RecyclerView = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)

        async() {
            val result = RequestForecastCommand("94110").execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result)
            }
        }
    }

}

public class ForecastRequest(val zipCode: String) {

    companion object {
        private val APP_ID = "f670c5836b940e03068ca297fd339c43"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    public fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}

public class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}
