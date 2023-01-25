package com.haque.baseline.di

import com.haque.baseline.data.source.source.remote.WeatherApi
import com.haque.baseline.data.source.source.repository.WeatherRepository
import com.haque.baseline.data.source.source.repository.WeatherRepositoryImpl
import com.haque.baseline.ui.CurrentWeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    abstract fun provideWeatherAPI(weatherAPI: WeatherApi): WeatherApi
}
