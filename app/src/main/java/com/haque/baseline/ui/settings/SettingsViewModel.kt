package com.haque.baseline.ui.settings

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.haque.baseline.data.TemperatureConstants
import com.haque.baseline.data.TemperatureConstants.celsiusScale
import com.haque.baseline.data.TemperatureConstants.fahrenheitScale
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    ViewModel() {

    // This is a getter
    fun isCelsius(): Boolean {
        return sharedPreferences.getString(
            TemperatureConstants.temperatureScaleKey,
            TemperatureConstants.fahrenheitScale
        ) == celsiusScale
    }

    // This is a Setter
    fun setCelsius() {
        sharedPreferences.edit()
            .putString(TemperatureConstants.temperatureScaleKey, TemperatureConstants.celsiusScale)
            .apply()
    }

    // Getter
    fun isFahrenheit(): Boolean {
        return sharedPreferences.getString(
            TemperatureConstants.temperatureScaleKey,
            TemperatureConstants.celsiusScale
        ) == fahrenheitScale
    }

    // Setter
    fun setFahrenheit() {
        sharedPreferences.edit().putString(
            TemperatureConstants.temperatureScaleKey,
            TemperatureConstants.fahrenheitScale
        ).apply()
    }
}

/*
isCelsius returns a boolean.  it's a getter
if(isCelsius == false) // current scale is Fahrenheit
if(isCelsius) // current scale is celsius

it will be set after the first call to setCelsius
otherwise it will use the default value
sharedpreferences is saved until the user clear their caches or reinstalls the app
 */