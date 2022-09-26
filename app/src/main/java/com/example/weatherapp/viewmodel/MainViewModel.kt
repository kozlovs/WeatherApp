package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.DayItem

class MainViewModel {
    val liveDataCurrent = MutableLiveData<String>()
    val liveDataList = MutableLiveData<List<DayItem>>()
}