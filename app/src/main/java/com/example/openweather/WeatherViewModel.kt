package com.example.openweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    val weatherData = MutableLiveData<WeatherData?>()

    private val apiService = ApiService.create()

    fun fetchWeatherData(lat: Double, lon: Double, apiKey: String) {
        viewModelScope.launch {
            val data = apiService.getWeatherData(lat, lon, apiKey)
            weatherData.value = data
        }
    }
}