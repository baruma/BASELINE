package com.haque.baseline.data.model

import java.time.LocalDateTime

/*

Basically, what do you want to display big, front and center?

Right now I modeled all the domain models directly after their data models, however, they can be
mixed and matched to represent data corresponding with their UI.

Try and get the domain models to represent their UI components the best you can, so do all the mapping
you need to do so these classes end up clean.

Missing:

- Humidity
- Precipitation

 */

data class CurrentWeatherData (
    val time: LocalDateTime,
    val temperatureInFahrenheit: Double,
    val windSpeed: Double,
    val windDirection: Double,
    val weatherCode: Int
    )