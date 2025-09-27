package com.emirerkut.domain.usecase

import com.emirerkut.data.repository.MovieRepository
import com.emirerkut.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Movie> {
        return repository.getMovieDetail(movieId)
    }
}
