package com.haque.baseline.ui.settings

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.haque.baseline.data.UnitConstants
import com.haque.baseline.data.UnitConstants.metricScale
import com.haque.baseline.data.UnitConstants.imperialScale
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    ViewModel() {

    // This is a getter
    fun isMetric(): Boolean {
        return sharedPreferences.getString(
            UnitConstants.unitScaleKey,
            UnitConstants.imperialScale
        ) == metricScale
    }

    // This is a Setter
    fun setMetric() {
        sharedPreferences.edit()
            .putString(UnitConstants.unitScaleKey, UnitConstants.metricScale)
            .apply()
    }

    // Getter
    fun isImperial(): Boolean {
        return sharedPreferences.getString(
            UnitConstants.unitScaleKey,
            UnitConstants.imperialScale
        ) == imperialScale
    }

    // Setter
    fun setImperial() {
        sharedPreferences.edit().putString(
            UnitConstants.unitScaleKey,
            UnitConstants.imperialScale
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