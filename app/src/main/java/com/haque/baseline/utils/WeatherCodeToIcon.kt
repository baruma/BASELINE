package com.haque.baseline.utils

import androidx.annotation.DrawableRes
import com.haque.baseline.R

sealed class WeatherCodeToIcon(
    val weatherDescription: String,
    @DrawableRes val iconResource: Int
) {
    object ClearSky : WeatherCodeToIcon(
        weatherDescription = "Clear Sky",
        iconResource = R.drawable.sun_round_white
    )
    object MainlyClear : WeatherCodeToIcon(
        weatherDescription = "Mainly clear",
        iconResource = R.drawable.sun_round_white
    )
    object PartlyCloudy : WeatherCodeToIcon(
        weatherDescription = "Partly cloudy",
        iconResource = R.drawable.cloud_round_white
    )
    object Overcast : WeatherCodeToIcon(
        weatherDescription = "Overcast",
        iconResource = R.drawable.cloud_round_white
    )
    object Foggy : WeatherCodeToIcon(
        weatherDescription = "Foggy",
        iconResource = R.drawable.fog_round_white_24
    )
    object DepositingRimeFog : WeatherCodeToIcon(
        weatherDescription = "Depositing rime fog",
        iconResource = R.drawable.fog_round_white_24
    )
    object LightDrizzle : WeatherCodeToIcon(
        weatherDescription = "Light drizzle",
        iconResource = R.drawable.precipitation_round_white
    )
    object ModerateDrizzle : WeatherCodeToIcon(
        weatherDescription = "Moderate drizzle",
        iconResource = R.drawable.precipitation_round_white
    )
    object DenseDrizzle : WeatherCodeToIcon(
        weatherDescription = "Dense drizzle",
        iconResource = R.drawable.precipitation_round_white
    )
    object LightFreezingDrizzle : WeatherCodeToIcon(
        weatherDescription = "Slight freezing drizzle",
        iconResource = R.drawable.precipitation_round_white
    )
    object DenseFreezingDrizzle : WeatherCodeToIcon(
        weatherDescription = "Dense freezing drizzle",
        iconResource = R.drawable.precipitation_round_white
    )
    object SlightRain : WeatherCodeToIcon(
        weatherDescription = "Slight rain",
        iconResource = R.drawable.precipitation_round_white
    )
    object ModerateRain : WeatherCodeToIcon(
        weatherDescription = "Rainy",
        iconResource = R.drawable.precipitation_round_white
    )
    object HeavyRain : WeatherCodeToIcon(
        weatherDescription = "Heavy rain",
        iconResource = R.drawable.precipitation_round_white
    )
    object HeavyFreezingRain: WeatherCodeToIcon(
        weatherDescription = "Heavy freezing rain",
        iconResource = R.drawable.precipitation_round_white
    )
    object SlightSnowFall: WeatherCodeToIcon(
        weatherDescription = "Slight snow fall",
        iconResource = R.drawable.precipitation_round_white
    )
    object ModerateSnowFall: WeatherCodeToIcon(
        weatherDescription = "Moderate snow fall",
        iconResource = R.drawable.precipitation_round_white
    )
    object HeavySnowFall: WeatherCodeToIcon(
        weatherDescription = "Heavy snow fall",
        iconResource = R.drawable.precipitation_round_white
    )
    object SnowGrains: WeatherCodeToIcon(
        weatherDescription = "Snow grains",
        iconResource = R.drawable.precipitation_round_white
    )
    object SlightRainShowers: WeatherCodeToIcon(
        weatherDescription = "Slight rain showers",
        iconResource = R.drawable.precipitation_round_white
    )
    object ModerateRainShowers: WeatherCodeToIcon(
        weatherDescription = "Moderate rain showers",
        iconResource = R.drawable.precipitation_round_white
    )
    object ViolentRainShowers: WeatherCodeToIcon(
        weatherDescription = "Violent rain showers",
        iconResource = R.drawable.precipitation_round_white
    )
    object SlightSnowShowers: WeatherCodeToIcon(
        weatherDescription = "Light snow showers",
        iconResource = R.drawable.precipitation_round_white
    )
    object HeavySnowShowers: WeatherCodeToIcon(
        weatherDescription = "Heavy snow showers",
        iconResource = R.drawable.precipitation_round_white
    )
    object ModerateThunderstorm: WeatherCodeToIcon(
        weatherDescription = "Moderate thunderstorm",
        iconResource = R.drawable.bolt_round_white
    )
    object SlightHailThunderstorm: WeatherCodeToIcon(
        weatherDescription = "Thunderstorm with slight hail",
        iconResource = R.drawable.bolt_round_white
    )
    object HeavyHailThunderstorm: WeatherCodeToIcon(
        weatherDescription = "Thunderstorm with heavy hail",
        iconResource = R.drawable.bolt_round_white
    )

    companion object {
        fun codeToIconMapper(code: Int): WeatherCodeToIcon {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}