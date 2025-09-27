package com.emirerkut.domain.usecase

import com.emirerkut.data.repository.MovieRepository
import com.emirerkut.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun execute(language: String): Flow<List<Movie>> = repository.getNowPlayingMovies(language)
}