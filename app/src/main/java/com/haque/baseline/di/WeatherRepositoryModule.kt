//package com.haque.baseline.di
//
//import com.haque.baseline.domain.WeatherRepository
//import com.haque.baseline.data.source.source.repository.WeatherRepositoryImpl
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class RepositoryModule {
//
//    @Binds
//    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
//}