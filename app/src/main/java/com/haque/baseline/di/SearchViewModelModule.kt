package com.haque.baseline.di

import android.content.Context
import com.haque.baseline.utils.GeocoderWrapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
abstract class SearchViewModelModule {
//    @Binds
//    @Singleton
//    abstract fun bindGeocoderWrapper(@ApplicationContext context: Context): GeocoderWrapper

}