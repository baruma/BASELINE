package com.haque.baseline.di

import com.haque.baseline.data.source.source.repository.PlaceRepositoryImpl
import com.haque.baseline.domain.PlaceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class SearchViewModelModule {
    @Binds
    abstract fun bindPlaceRepositoryImpl(placeRepositoryImpl: PlaceRepositoryImpl): PlaceRepository
}