package com.example.openweather

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private lateinit var resultTxt: TextView
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var backgroundImage: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultTxt = findViewById(R.id.resultTxt)
        backgroundImage = findViewById(R.id.backgroundImage)

        viewModel.weatherData.observe(this, Observer { data ->
            if (data != null) {
                Log.e("WEATHER", data.toString())
                resultTxt.text = data.main.temp.toString() + " " + data.weather[0].main.toString() + " " + data.name

                when (data.weather[0].main) {
                    "Clear" -> backgroundImage.setImageResource(R.drawable.outline_wb_sunny_24)
                    "Rain" -> backgroundImage.setImageResource(R.drawable.outline_cloud_24)
                    "Clouds" -> backgroundImage.setImageResource(R.drawable.outline_cloud_24)
                }
            }
        })

        viewModel.fetchWeatherData(
            lat = 49.24,
            lon = 28.45,
            apiKey = "05cf707c1eb9930868bc638c4f224b9d"
        )
    }
}

