package com.emirerkut.domain.di

import com.emirerkut.data.repository.MovieRepository
import com.emirerkut.domain.usecase.GetPopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(repository: MovieRepository) =
        GetPopularMoviesUseCase(repository)
}