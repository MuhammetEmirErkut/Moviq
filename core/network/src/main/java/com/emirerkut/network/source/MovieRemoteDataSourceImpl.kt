package com.emirerkut.network.source

import com.emirerkut.network.model.MovieResponseDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val api: RetrofitService
) : MovieRemoteDataSource {

    override fun getPopularMovies(language: String): Flow<Response<MovieResponseDTO>> = flow {
        emit(api.getPopularMovies(language = language))
    }
}