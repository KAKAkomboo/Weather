package com.example.openweather

data class WeatherData(
    val weather: List<Weather>,
    val main: Main,
    val name: String
)

data class Weather(
    val main: String,
    val description: String
)

data class Main(
    val temp: Float,
    val pressure: Int,
    val humidity: Int
)