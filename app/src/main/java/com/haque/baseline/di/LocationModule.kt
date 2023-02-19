//package com.haque.baseline.di
//
//import com.haque.baseline.domain.PlaceFinder
//import com.haque.baseline.service.LocationFinderService
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import javax.inject.Singleton
//
//@ExperimentalCoroutinesApi
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class LocationModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindLocationFinder(locationFinderService: LocationFinderService): PlaceFinder
//}