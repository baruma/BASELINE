package com.haque.baseline.ui.settings

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.haque.baseline.data.TemperatureConstants
import com.haque.baseline.data.TemperatureConstants.celsiusScale
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    ViewModel() {

    fun isCelsius(): Boolean {
        return sharedPreferences.getString(
            TemperatureConstants.temperatureScaleKey,
            TemperatureConstants.fahrenheitScale
        ) == celsiusScale
    }

    fun setCelsius() {
        sharedPreferences.edit()
            .putString(TemperatureConstants.temperatureScaleKey, TemperatureConstants.celsiusScale)
            .apply()
    }

    fun setFahrenheit() {
        sharedPreferences.edit().putString(
            TemperatureConstants.temperatureScaleKey,
            TemperatureConstants.fahrenheitScale
        ).apply()
    }
}