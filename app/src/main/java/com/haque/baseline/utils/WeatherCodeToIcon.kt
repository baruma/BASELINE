package com.haque.baseline.utils

import androidx.annotation.DrawableRes
import com.haque.baseline.R

sealed class WeatherCodeToIcon(
    val weatherDescription: String,
    @DrawableRes val iconResource: Int
) {
    object ClearSky : WeatherCodeToIcon(
        weatherDescription = "Clear skies - a beautiful day.",
        iconResource = R.drawable.sun_round
    )
    object MainlyClear : WeatherCodeToIcon(
        weatherDescription = "Mostly clear - not too bad, right?",
        iconResource = R.drawable.sun_round
    )
    object PartlyCloudy : WeatherCodeToIcon(
        weatherDescription = "Partly cloudy - that's just free shade (or a spotty tan)",
        iconResource = R.drawable.cloud_round
    )
    object Overcast : WeatherCodeToIcon(
        weatherDescription = "Overcast weather just means you save sunscreen",
        iconResource = R.drawable.cloud_round
    )
    object Foggy : WeatherCodeToIcon(
        weatherDescription = "Foggy - angst angst angst.",
        iconResource = R.drawable.fog_round
    )
    object DepositingRimeFog : WeatherCodeToIcon(
        weatherDescription = "Dense fog",
        iconResource = R.drawable.fog_round
    )
    object LightDrizzle : WeatherCodeToIcon(
        weatherDescription = "Drizzle... you get free shower, I guess?",
        iconResource = R.drawable.precipitation_round
    )
    object ModerateDrizzle : WeatherCodeToIcon(
        weatherDescription = "Rain - Save up on your water bill",
        iconResource = R.drawable.precipitation_round
    )
    object DenseDrizzle : WeatherCodeToIcon(
        weatherDescription = "Heavy Rain - the plants aren't drowning, they're stocking up.",
        iconResource = R.drawable.precipitation_round
    )
    object LightFreezingDrizzle : WeatherCodeToIcon(
        weatherDescription = "Freezing Rain",
        iconResource = R.drawable.precipitation_round
    )
    object DenseFreezingDrizzle : WeatherCodeToIcon(
        weatherDescription = "Freezing Rain",
        iconResource = R.drawable.precipitation_round
    )
    object SlightRain : WeatherCodeToIcon(
        weatherDescription = "Rain. Just rain. I have nothing for this.",
        iconResource = R.drawable.precipitation_round
    )
    object ModerateRain : WeatherCodeToIcon(
        weatherDescription = "Rain.",
        iconResource = R.drawable.precipitation_round
    )
    object HeavyRain : WeatherCodeToIcon(
        weatherDescription = "Heavy Rain. Curl up by a window.",
        iconResource = R.drawable.precipitation_round
    )
    object HeavyFreezingRain: WeatherCodeToIcon(
        weatherDescription = "Heavy Freezing Rain - Curl up by a window in a blanket",
        iconResource = R.drawable.precipitation_round
    )
    object SlightSnowFall: WeatherCodeToIcon(
        weatherDescription = "Slight Snow",
        iconResource = R.drawable.precipitation_round
    )
    object ModerateSnowFall: WeatherCodeToIcon(
        weatherDescription = "Moderate Snow",
        iconResource = R.drawable.precipitation_round
    )
    object HeavySnowFall: WeatherCodeToIcon(
        weatherDescription = "Heavy Snow. Thick land clouds.",
        iconResource = R.drawable.precipitation_round
    )
    object SnowGrains: WeatherCodeToIcon(
        weatherDescription = "Snow",
        iconResource = R.drawable.precipitation_round
    )
    object SlightRainShowers: WeatherCodeToIcon(
        weatherDescription = "Slight Rain",
        iconResource = R.drawable.precipitation_round
    )
    object ModerateRainShowers: WeatherCodeToIcon(
        weatherDescription = "Moderate Rain",
        iconResource = R.drawable.precipitation_round
    )
    object ViolentRainShowers: WeatherCodeToIcon(
        weatherDescription = "Violent Rain",
        iconResource = R.drawable.precipitation_round
    )
    object SlightSnowShowers: WeatherCodeToIcon(
        weatherDescription = "Light Snow",
        iconResource = R.drawable.precipitation_round
    )
    object HeavySnowShowers: WeatherCodeToIcon(
        weatherDescription = "Heavy Snow",
        iconResource = R.drawable.precipitation_round
    )
    object ModerateThunderstorm: WeatherCodeToIcon(
        weatherDescription = "Moderate Thunderstorm",
        iconResource = R.drawable.bolt_round
    )
    object SlightHailThunderstorm: WeatherCodeToIcon(
        weatherDescription = "Thunderstorm with Hail",
        iconResource = R.drawable.bolt_round
    )
    object HeavyHailThunderstorm: WeatherCodeToIcon(
        weatherDescription = "Thunderstorm with Hail",
        iconResource = R.drawable.bolt_round
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