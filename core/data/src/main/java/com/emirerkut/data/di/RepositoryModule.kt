package com.emirerkut.data.di

import com.emirerkut.data.repository.MovieRepository
import com.emirerkut.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository
}