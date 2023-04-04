package com.haque.baseline.ui.weather.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.haque.baseline.R
import com.haque.baseline.databinding.DetailFragmentBinding
import com.haque.baseline.ui.search.SearchViewModel
import com.haque.baseline.ui.weather.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

// Is this AndroidEntryPoint necessary?
@AndroidEntryPoint
class DetailFragment: Fragment() {
    private lateinit var binding: DetailFragmentBinding

    private val sharedWeatherViewModel by activityViewModels<WeatherViewModel>()
    private val sharedSearchViewModel: SearchViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.detail_fragment, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            view.findNavController().navigate(R.id.weather)
        }

        // TODO: Re-write this so that it can know of unit changes from SharedPreferences
        // TODO: Re-write bindings so that the bang operators are removed.
        // TODO: Check if DailyForecasted includes current day - otherwise some UI will need to be rewritten.

        binding.detailCityTextview.text = sharedSearchViewModel.selectedPlace.value?.city ?: sharedWeatherViewModel.defaultCurrentLocation.value!!.city

        binding.detailDescriptionTextview.text = sharedWeatherViewModel.oneCallWeatherPayload.value!!.currentWeather.weatherCode.weatherDescription
        binding.detailWeatherImageview.setImageResource(sharedWeatherViewModel.oneCallWeatherPayload.value!!.currentWeather.weatherCode.iconResource)
        binding.detailTemperatureTextview.text = sharedWeatherViewModel.oneCallWeatherPayload.value!!.currentWeather.temperatureInFahrenheit.toString()
        binding.maxTempTextview.text = sharedWeatherViewModel.dailyForecastedWeatherData.value!!.first().maxTemperature.toString()
        binding.minTempTextview.text = sharedWeatherViewModel.dailyForecastedWeatherData.value!!.first().minTemperature.toString()

        // 1st Linear View
        binding.precipitationPercentageTextview.text = sharedWeatherViewModel.hourlyWeatherData.value!!.first().precipitation.toString()
        binding.precipitationSumTextview.text = sharedWeatherViewModel.dailyForecastedWeatherData.value!!.first().precipitationSum.toString()
        binding.humidityTextview.text = sharedWeatherViewModel.hourlyWeatherData.value!!.first().humidity.toString()

        // 2nd Linear View
        // TODO: Retrieve hourly index that matches current hour / hour rounded back
        binding.visibilityTextview.text = sharedWeatherViewModel.hourlyWeatherData.value!!.first().visibility.toString()
        binding.windspeedTextview.text = sharedWeatherViewModel.oneCallWeatherPayload.value!!.currentWeather.windSpeed.toString()
        binding.windspeedDirectionTextview.text = sharedWeatherViewModel.oneCallWeatherPayload.value!!.currentWeather.windDirection.toString()
    }
}