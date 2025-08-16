package com.emirerkut.data.repository

import com.emirerkut.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(language: String): Flow<List<Movie>>
    fun getTopRatedMovies(language: String): Flow<List<Movie>>
    fun searchMovies(query: String): Flow<List<Movie>>
}