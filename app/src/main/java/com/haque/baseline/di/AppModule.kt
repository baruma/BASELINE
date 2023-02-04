package com.haque.baseline.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.haque.baseline.data.source.source.remote.WeatherApi
import com.haque.baseline.data.source.source.repository.WeatherRepository
import com.haque.baseline.data.source.source.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

/*
You declare modules for individual purposes, like broadcast modules, auth modules, etc.
You can declare modules for specific scopes.  Whatever works for the purpose of the app.
AppModule is different it seems.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    // Dagger-Hilt knows how to create this type of class.  Whenever you request an instance of
    // your api, dagger hilt will know to look for it and provide it.
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
                // https://api.open-meteo.com/v1/forecast?latitude=37.76&longitude=-122.39&hourly=temperature_2m,relativehumidity_2m,apparent_temperature,precipitation,visibility,weathercode,windspeed_10m&daily=weathercode,temperature_2m_max,temperature_2m_min,precipitation_sum,precipitation_hours&current_weather=true&temperature_unit=fahrenheit&windspeed_unit=mph&precipitation_unit=inch&timezone=auto
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }
}