package com.jiwon.weatherapp.model

object LocationResultModel {

    data class Location(
        val title: String,              // "Seoul"
        val location_type: String,      // "City"
        val woeid: Int,                 // 1132599
        val latt_long: String           // "37.557121,126.977379"
    )

}