package com.haque.baseline.di

import com.haque.baseline.data.source.source.repository.WeatherRepositoryImpl
import com.haque.baseline.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class ViewModelModule {

    // This function is redundant.  Remove during code clean up.
    @Binds
    abstract fun bindWeatherRepositoryImpl(
        weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}

