package com.haque.baseline.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haque.baseline.R
import com.haque.baseline.data.model.DailyForecastedData
import com.haque.baseline.data.model.HourlyWeatherData
import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.databinding.WeatherFragmentBinding
import com.haque.baseline.ui.search.SearchViewModel
import com.haque.baseline.ui.weather.daily.DailyWeatherRecyclerAdapter
import com.haque.baseline.ui.weather.hourly.HourlyRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


@AndroidEntryPoint
class WeatherFragment : Fragment() {
//    private val currentWeatherViewModel by viewModels<WeatherViewModel>()
    private val sharedSearchViewModel: SearchViewModel by activityViewModels()
    private val sharedWeatherViewModel by activityViewModels<WeatherViewModel>()

    private lateinit var hourlyWeatherRecyclerAdapter: HourlyRecyclerAdapter
    private lateinit var dailyWeatherRecyclerAdapter: DailyWeatherRecyclerAdapter

    private lateinit var binding: WeatherFragmentBinding

    private val currentPlaceObserver: Observer<PlaceData> =
        Observer<PlaceData> {
//            Use lifecycle scope like below so once the fragment is out of scope, the observer wont be called.
            lifecycleScope.launch(IO) {
                sharedWeatherViewModel.getOneCallWeatherData(it.lat, it.lon)
            }
        }

    private val hourlyWeatherObserver: Observer<List<HourlyWeatherData>> =
        Observer<List<HourlyWeatherData>> { hourlyData ->
            hourlyWeatherRecyclerAdapter.updateRecyclerDate(hourlyData)
        }

    private val dailyWeatherObserver: Observer<List<DailyForecastedData>> =
        Observer<List<DailyForecastedData>> { dailyForecastedWeather ->
            dailyWeatherRecyclerAdapter.updateRecyclerData(dailyForecastedWeather)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.weather_fragment, container, false
        )
        return binding.root
    }

//    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.currentWeatherView.setOnClickListener {
            view.findNavController().navigate(R.id.detail)
        }

        // Recyclers
        hourlyWeatherRecyclerAdapter = HourlyRecyclerAdapter(mutableListOf())
        binding.hourlyWeatherRecyclerview.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        binding.hourlyWeatherRecyclerview.adapter = hourlyWeatherRecyclerAdapter

        dailyWeatherRecyclerAdapter = DailyWeatherRecyclerAdapter(mutableListOf())
        binding.dailyWeatherForecastRecyclerview.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        binding.dailyWeatherForecastRecyclerview.adapter = dailyWeatherRecyclerAdapter

        // Current Weather Card
        binding.cityTextview.text = sharedSearchViewModel.selectedPlace.value?.city ?: "New York"

        // Observers
        sharedSearchViewModel.placeData.observe(viewLifecycleOwner) { places ->
            binding.cityTextview.text = places.first().city
        }

        sharedWeatherViewModel.oneCallWeatherPayload.observe(viewLifecycleOwner) { onecallPayload ->
            binding.currentTemperatureTextview.text =
                ("${onecallPayload.currentWeather.temperatureInFahrenheit}°")

            binding.descriptionTextview.text = onecallPayload.currentWeather.weatherCode.weatherDescription
            binding.weatherIconImageview.setImageResource(onecallPayload.currentWeather.weatherCode.iconResource)
        }

        sharedWeatherViewModel.hourlyWeatherData.observe(
            viewLifecycleOwner,
            hourlyWeatherObserver
        )

        sharedWeatherViewModel.dailyForecastedWeatherData.observe(
            viewLifecycleOwner,
            dailyWeatherObserver
        )

        sharedWeatherViewModel.defaultCurrentLocation.observe(
            viewLifecycleOwner, currentPlaceObserver
        )

        lifecycleScope.launch(IO) {
            getWeatherFromCurrentLocation()
            getWeatherFromSearch()
        }
    }

    private suspend fun getWeatherFromSearch() {
        val latitude = sharedSearchViewModel.selectedPlace.value?.lat ?: 43.00f
        val longitude = sharedSearchViewModel.selectedPlace.value?.lon ?: -75.00f
        sharedWeatherViewModel.getOneCallWeatherData(latitude, longitude)
    }

    private suspend fun getWeatherFromCurrentLocation() {
        val currentLocation = sharedWeatherViewModel.defaultCurrentLocation.value!!
        sharedWeatherViewModel.getOneCallWeatherData(currentLocation.lat, currentLocation.lon)
    }
}