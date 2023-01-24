package com.haque.baseline.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// Making modules for viewmodels can be tricky.
// First we need a Factory to make the viewmodel.
//
//@InstallIn(SingletonComponent::class)
//@Module
//abstract class ViewModelModule {
//    @Binds
//    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
//
//}

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {

}