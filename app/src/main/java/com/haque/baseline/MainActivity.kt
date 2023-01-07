package com.haque.baseline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.haque.baseline.data.source.source.dto.OneCallWeatherPayloadDTO
import com.haque.baseline.data.source.source.remote.WeatherApi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}