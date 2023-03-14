package com.haque.baseline.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.haque.baseline.R
import com.haque.baseline.databinding.SettingsFragmentBinding
import com.haque.baseline.ui.weather.WeatherViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SettingsFragment: Fragment() {

    private lateinit var binding: SettingsFragmentBinding

    private val settingsViewModel by viewModels<SettingsViewModel>()
    private val sharedWeatherViewModel by activityViewModels<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.settings_fragment, container, false)

        onSwitchToggle()

        return binding.root
    }

    private fun onSwitchToggle() {
        if (binding.unitConverterSwitch.isChecked) {
            settingsViewModel.isCelsius()
        } else {
            settingsViewModel.isFahrenheit()
        }

        binding.unitConverterSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                settingsViewModel.setCelsius()

            } else {
                settingsViewModel.setFahrenheit()
            }
        }
    }
}