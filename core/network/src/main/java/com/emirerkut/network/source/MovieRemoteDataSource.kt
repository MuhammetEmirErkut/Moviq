package com.emirerkut.network.source

import com.emirerkut.network.model.MovieDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRemoteDataSource {
    fun getPopularMovies(language: String): Flow<Response<MovieDTO>>
}