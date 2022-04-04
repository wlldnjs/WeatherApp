package com.jiwon.weatherapp.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jiwon.weatherapp.R
import com.jiwon.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this@MainActivity, R.layout.activity_main) }
    private val mViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.run {
            lifecycleOwner = this@MainActivity
            viewModel = mViewModel
            mViewModel.isLoading.observe(this@MainActivity) { isLoading ->
                swipeWeather.isRefreshing = isLoading
            }
            swipeWeather.setOnRefreshListener {
                mViewModel.getWeather()
            }
            recyclerWeather.adapter = WeatherRecyclerAdapter()
        }
    }
}