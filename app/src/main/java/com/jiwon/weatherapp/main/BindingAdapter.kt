package com.jiwon.weatherapp.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jiwon.weatherapp.model.WeatherResultModel

@BindingAdapter("isLoading", "item")
fun setWeatherItem(recyclerView: RecyclerView, isLoading: Boolean, item: List<WeatherResultModel.Result>?) {
    if (!isLoading && item != null) {
        (recyclerView.adapter as? WeatherRecyclerAdapter)?.setItems(item)
    }
}

@BindingAdapter("loadImageUrl")
fun loadImageUrl(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView).load(imageUrl).into(imageView)
    }
}