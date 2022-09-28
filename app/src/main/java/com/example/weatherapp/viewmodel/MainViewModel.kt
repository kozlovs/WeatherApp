package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.adapters.WeatherModel

class MainViewModel {
    val liveDataCurrent = MutableLiveData<WeatherModel>()
    val liveDataList = MutableLiveData<List<WeatherModel>>()
}