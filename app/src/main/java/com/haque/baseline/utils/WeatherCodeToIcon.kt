package com.haque.baseline.utils

import androidx.annotation.DrawableRes
import com.haque.baseline.R

sealed class WeatherCodeToIcon(
    val weatherDescription: String,
    @DrawableRes val iconResource: Int
) {
    object ClearSky : WeatherCodeToIcon(
        weatherDescription = "Clear skies - a beautiful day.",
        iconResource = R.drawable.sun_round_white
    )
    object MainlyClear : WeatherCodeToIcon(
        weatherDescription = "Mostly clear - not too bad, right?",
        iconResource = R.drawable.sun_round_white
    )
    object PartlyCloudy : WeatherCodeToIcon(
        weatherDescription = "Partly cloudy - that's just free shade (or a spotty tan)",
        iconResource = R.drawable.cloud_round_white
    )
    object Overcast : WeatherCodeToIcon(
        weatherDescription = "Overcast weather just means you save sunscreen",
        iconResource = R.drawable.cloud_round_white
    )
    object Foggy : WeatherCodeToIcon(
        weatherDescription = "Foggy - angst angst angst.",
        iconResource = R.drawable.fog_round_white
    )
    object DepositingRimeFog : WeatherCodeToIcon(
        weatherDescription = "Dense fog",
        iconResource = R.drawable.fog_round_white
    )
    object LightDrizzle : WeatherCodeToIcon(
        weatherDescription = "Drizzle... you get free shower, I guess?",
        iconResource = R.drawable.precipitation_round_white
    )
    object ModerateDrizzle : WeatherCodeToIcon(
        weatherDescription = "Rain - Save up on your water bill",
        iconResource = R.drawable.precipitation_round_white
    )
    object DenseDrizzle : WeatherCodeToIcon(
        weatherDescription = "Heavy Rain - the plants aren't drowning, they're stocking up.",
        iconResource = R.drawable.precipitation_round_white
    )
    object LightFreezingDrizzle : WeatherCodeToIcon(
        weatherDescription = "Freezing Rain",
        iconResource = R.drawable.precipitation_round_white
    )
    object DenseFreezingDrizzle : WeatherCodeToIcon(
        weatherDescription = "Freezing Rain",
        iconResource = R.drawable.precipitation_round_white
    )
    object SlightRain : WeatherCodeToIcon(
        weatherDescription = "Rain. Just rain. I have nothing for this.",
        iconResource = R.drawable.precipitation_round_white
    )
    object ModerateRain : WeatherCodeToIcon(
        weatherDescription = "Rain.",
        iconResource = R.drawable.precipitation_round_white
    )
    object HeavyRain : WeatherCodeToIcon(
        weatherDescription = "Heavy Rain. Curl up by a window.",
        iconResource = R.drawable.precipitation_round_white
    )
    object HeavyFreezingRain: WeatherCodeToIcon(
        weatherDescription = "Heavy Freezing Rain - Curl up by a window in a blanket",
        iconResource = R.drawable.precipitation_round_white
    )
    object SlightSnowFall: WeatherCodeToIcon(
        weatherDescription = "Slight Snow",
        iconResource = R.drawable.precipitation_round_white
    )
    object ModerateSnowFall: WeatherCodeToIcon(
        weatherDescription = "Moderate Snow",
        iconResource = R.drawable.precipitation_round_white
    )
    object HeavySnowFall: WeatherCodeToIcon(
        weatherDescription = "Heavy Snow. Thick land clouds.",
        iconResource = R.drawable.precipitation_round_white
    )
    object SnowGrains: WeatherCodeToIcon(
        weatherDescription = "Snow",
        iconResource = R.drawable.precipitation_round_white
    )
    object SlightRainShowers: WeatherCodeToIcon(
        weatherDescription = "Slight Rain",
        iconResource = R.drawable.precipitation_round_white
    )
    object ModerateRainShowers: WeatherCodeToIcon(
        weatherDescription = "Moderate Rain",
        iconResource = R.drawable.precipitation_round_white
    )
    object ViolentRainShowers: WeatherCodeToIcon(
        weatherDescription = "Violent Rain",
        iconResource = R.drawable.precipitation_round_white
    )
    object SlightSnowShowers: WeatherCodeToIcon(
        weatherDescription = "Light Snow",
        iconResource = R.drawable.precipitation_round_white
    )
    object HeavySnowShowers: WeatherCodeToIcon(
        weatherDescription = "Heavy Snow",
        iconResource = R.drawable.precipitation_round_white
    )
    object ModerateThunderstorm: WeatherCodeToIcon(
        weatherDescription = "Moderate Thunderstorm",
        iconResource = R.drawable.bolt_round_white
    )
    object SlightHailThunderstorm: WeatherCodeToIcon(
        weatherDescription = "Thunderstorm with Hail",
        iconResource = R.drawable.bolt_round_white
    )
    object HeavyHailThunderstorm: WeatherCodeToIcon(
        weatherDescription = "Thunderstorm with Hail",
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