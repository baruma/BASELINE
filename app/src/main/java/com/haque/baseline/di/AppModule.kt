package com.haque.baseline.di

import com.haque.baseline.data.source.source.remote.WeatherApi
import com.haque.baseline.data.source.source.repository.WeatherRepository
import com.haque.baseline.data.source.source.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
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
        // removed moshiconverterfactory here.
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .build()
            .create()
    }

    // This is the fusedlocationproviderclient needed by the defaultlocationtracker in data.location
    // You can go to the Location and Repository Modules
//    @Provides
//    @Singleton
//    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
//        return LocationServices.getFusedLocationProviderClient(app)
//    }

//    @Provides
//    @Singleton
//    // DaggerHilt is "smart" enough to detect you need WeatherAPI, so it will look in this object for it.
//    fun provideWeatherRepository(api: WeatherApi): WeatherRepository {
//        return WeatherRepositoryImpl(api)
//    }

}

