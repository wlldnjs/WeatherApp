package com.jiwon.weatherapp.model

object WeatherResultModel {

    data class Result(
        var consolidated_weather: List<Weather>,
        val title: String
    )

    data class Weather(
        val weather_state_name: String,     // 날씨 요약
        var weather_state_abbr: String,     // 아이콘 이미지 정보
        val the_temp: Float,                // 현재 날씨
        val humidity: String                // 습도
    )
}