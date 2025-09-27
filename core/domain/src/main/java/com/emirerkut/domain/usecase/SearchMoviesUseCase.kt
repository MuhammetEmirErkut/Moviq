package com.emirerkut.domain.usecase

import com.emirerkut.model.Movie
import com.emirerkut.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun execute(language: String): Flow<List<Movie>> = repository.searchMovies(language)
}
