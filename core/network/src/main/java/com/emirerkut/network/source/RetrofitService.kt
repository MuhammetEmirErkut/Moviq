package com.emirerkut.network.source

import com.emirerkut.network.model.MovieResponseDTO
import com.emirerkut.network.util.Constants.LANGUAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = LANGUAGE
    ): Response<MovieResponseDTO>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String
    ): Response<MovieResponseDTO>
}