package com.haque.baseline.data.remote

/*
I like to describe this as a payload.  This is going to be broken down further into usable parts.
https://api.open-meteo.com/v1/forecast?latitude=37.76&longitude=-122.39&hourly=temperature_2m,

The Payload will be the Hourly data we'll be getting.  Everything that follows after '&hourly' is
data supplied by it.

I don't understand the naming convention of taking a payload and giving it a parent acronym naming
convention like, DTO, and then every successive data class to come after that is explicitely labeled
as Weather

 */
data class HourlyWeatherDTO (
    private val weatherDataDTO: WeatherDataDTO
)

