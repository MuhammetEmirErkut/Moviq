package com.emirerkut.network.source

import com.emirerkut.network.model.MovieDTO
import com.emirerkut.network.model.MovieResponseDTO
import com.emirerkut.network.util.Constants.LANGUAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = LANGUAGE
    ): Response<MovieResponseDTO>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = LANGUAGE
    ): Response<MovieResponseDTO>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = LANGUAGE
    ): Response<MovieResponseDTO>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = LANGUAGE
    ): Response<MovieResponseDTO>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String
    ): Response<MovieResponseDTO>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = LANGUAGE,
        @Query("append_to_response") appendToResponse: String = "credits"
    ): Response<MovieDTO>

}
