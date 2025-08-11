package com.emirerkut.data.mapper

import com.emirerkut.model.Movie
import com.emirerkut.network.model.MovieDTO
import com.emirerkut.network.model.MovieResponseDTO

fun MovieDTO.toMovie() = Movie(
    id = id ?: -1,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    posterPath = posterPath
)

fun MovieResponseDTO.toMovieList(): List<Movie> {
    return results?.map { it.toMovie() } ?: emptyList()
}