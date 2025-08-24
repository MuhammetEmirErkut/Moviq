package com.emirerkut.domain.usecase

import com.emirerkut.data.repository.MovieRepository
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun execute(language: String) = repository.getTopRatedMovies(language)
}