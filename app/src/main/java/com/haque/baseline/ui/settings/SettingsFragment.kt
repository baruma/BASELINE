package com.haque.baseline.ui.settings

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding: SettingsFragmentBinding

    private val settingsViewModel by viewModels<SettingsViewModel>()
    private val sharedWeatherViewModel by activityViewModels<WeatherViewModel>()

//    private val SWITCH_PREFS = "switch_prefs"
//    private val CELSIUS_STATUS = "celsius_on"
//
//    private val sharedPreferences = requireContext().getSharedPreferences(SWITCH_PREFS, MODE_PRIVATE)
//    private val editor = requireContext().getSharedPreferences(SWITCH_PREFS, MODE_PRIVATE).edit()
//
//    private val switch_status = sharedPreferences.getBoolean(CELSIUS_STATUS, false)


    // not injecting this instance because the fragment has access to context, whereas in the
    // SettingsVM, it did not, and accessing Application() is something I want to avoid.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.settings_fragment, container, false)

        // set listeners
        // set initial state

        setInitialToggleState()

        binding.unitConverterSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                settingsViewModel.setCelsius()

            } else {
                settingsViewModel.setFahrenheit()
            }
        }

        return binding.root
    }

    private fun setInitialToggleState() {
        binding.unitConverterSwitch.isChecked = !settingsViewModel.isFahrenheit()
    }
}