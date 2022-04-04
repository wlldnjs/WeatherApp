package com.jiwon.weatherapp.main

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jiwon.weatherapp.base.BaseRecyclerViewAdapter
import com.jiwon.weatherapp.base.BaseViewHolder
import com.jiwon.weatherapp.databinding.ItemWeatherBinding
import com.jiwon.weatherapp.databinding.ItemWeatherHeaderBinding
import com.jiwon.weatherapp.model.WeatherResultModel

class WeatherRecyclerAdapter : BaseRecyclerViewAdapter<WeatherResultModel.Result, RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = VIEW_TYPE_HEADER + 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            val binding = ItemWeatherHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            WeatherItemHeaderHolder(binding)
        } else {   // VIEW_TYPE_ITEM
            val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            WeatherItemHolder(binding)
        }
    }

    override fun onBindView(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WeatherItemHolder) {
            getItem(position - 1)?.let(holder::bind)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_ITEM
        }
    }

    inner class WeatherItemHeaderHolder(binding: ItemWeatherHeaderBinding) : BaseViewHolder<WeatherResultModel.Result>(binding.root) {
        override fun bind(item: WeatherResultModel.Result) {

        }
    }

    inner class WeatherItemHolder(private val binding: ItemWeatherBinding) : BaseViewHolder<WeatherResultModel.Result>(binding.root) {
        override fun bind(item: WeatherResultModel.Result) {
            binding.item = item

            item.consolidated_weather[0].run {
                val temp = the_temp.toInt().toString()
                binding.textTodayTemp.text = SpannableString("${temp}°C").apply {
                    setSpan(StyleSpan(Typeface.BOLD), 0, temp.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                binding.textTodayHumidity.text = SpannableString("${humidity}%").apply {
                    setSpan(StyleSpan(Typeface.BOLD), 0, humidity.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }

            item.consolidated_weather[1].run {
                val temp = the_temp.toInt().toString()
                binding.textTomorrowTemp.text = SpannableString("${temp}°C").apply {
                    setSpan(StyleSpan(Typeface.BOLD), 0, temp.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                binding.textTomorrowHumidity.text = SpannableString("${humidity}%").apply {
                    setSpan(StyleSpan(Typeface.BOLD), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }
        }
    }
}