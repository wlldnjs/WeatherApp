package com.jiwon.weatherapp.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jiwon.weatherapp.Constants
import com.jiwon.weatherapp.model.WeatherResultModel
import com.jiwon.weatherapp.network.LocationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val locationService: LocationService
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    var weatherList = ArrayList<WeatherResultModel.Result>()
    private val query = savedStateHandle.get<String>("query") ?: "se"

    init {
        getWeather()
    }

    fun getWeather() {
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                weatherList.clear()
                isLoading.value = true
            }
            locationService.getLocations(query).map {
                CoroutineScope(Dispatchers.IO).launch {
                    weatherList.add(locationService.getWeather(it.woeid).apply {
                        consolidated_weather = consolidated_weather.take(2).onEach {
                            it.weather_state_abbr =
                                Constants.WEATHER_ICON_ADDRESS_PREFIX + it.weather_state_abbr + Constants.WEATHER_ICON_ADDRESS_SUFFIX
                        }
                    })
                }
            }.joinAll()
            CoroutineScope(Dispatchers.Main).launch {
                isLoading.value = false
            }
        }
    }
}