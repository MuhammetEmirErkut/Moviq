package com.emirerkut.common.di

import com.emirerkut.common.helper.AppDispatcherProvider
import com.emirerkut.common.helper.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    abstract fun bindDispatcherProvider(appDispatcherProvider: AppDispatcherProvider): DispatcherProvider
}