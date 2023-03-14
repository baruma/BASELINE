package com.haque.baseline.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.haque.baseline.BuildConfig
import com.haque.baseline.data.TemperatureConstants
import com.haque.baseline.data.source.source.remote.WeatherApi
import com.haque.baseline.utils.GeocoderWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
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
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences(TemperatureConstants.temperatureScaleKey, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Singleton
    @Provides
    fun provideGeocoderWrapper(@ApplicationContext appContext: Context): GeocoderWrapper {
        return GeocoderWrapper(context = appContext)
    }
}