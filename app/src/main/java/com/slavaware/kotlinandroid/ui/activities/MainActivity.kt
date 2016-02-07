package com.slavaware.kotlinandroid.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.slavaware.kotlinandroid.R
import com.slavaware.kotlinandroid.domain.commands.RequestForecastCommand
import com.slavaware.kotlinandroid.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this)
        listOf(1, 2, 3).filter { it < 2 }
        async() {
            val result = RequestForecastCommand(94110).execute()
            uiThread {
                val adapter = ForecastListAdapter(result) { toast(it.description) }
                forecastList.adapter = adapter;
            }
        }
    }
}
