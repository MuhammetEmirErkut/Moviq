package com.emirerkut.domain.di

import com.emirerkut.data.repository.MovieRepository
import com.emirerkut.domain.usecase.GetPopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import com.emirerkut.domain.usecase.SearchMoviesUseCase
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(repository: MovieRepository) =
        GetPopularMoviesUseCase(repository)

    @Provides
    @Singleton
    fun provideSearchMoviesUseCase(repository: MovieRepository) =
        SearchMoviesUseCase(repository)
}