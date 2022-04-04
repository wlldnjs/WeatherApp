package com.jiwon.weatherapp.network

import com.jiwon.weatherapp.model.LocationResultModel
import com.jiwon.weatherapp.model.WeatherResultModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationService {
    @GET("/api/location/search/")
    suspend fun getLocations(@Query("query") query: String): List<LocationResultModel.Location>

    @GET("/api/location/{woeid}/")
    suspend fun getWeather(@Path("woeid") woeid: Int): WeatherResultModel.Result
}