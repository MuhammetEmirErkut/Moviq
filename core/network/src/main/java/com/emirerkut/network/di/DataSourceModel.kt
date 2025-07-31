package com.emirerkut.network.di

import com.emirerkut.network.source.MovieRemoteDataSource
import com.emirerkut.network.source.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModel {
    @Binds
    abstract fun provideRemoteDataSource(
        movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl
    ): MovieRemoteDataSource
}