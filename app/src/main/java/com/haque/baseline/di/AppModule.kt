package com.haque.baseline.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.haque.baseline.data.UnitConstants
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
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesForUnitConversion(app: Application): SharedPreferences {
        return app.getSharedPreferences(UnitConstants.unitScaleKey, Context.MODE_PRIVATE)
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