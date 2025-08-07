package com.emirerkut.ui.model

import com.emirerkut.model.Movie

data class MovieUiModel(
    val id: Int,
    val originalTitle: String?,
    val originalLanguage: String?,
    val posterPath: String?
)

fun Movie.toMovieUiModel() = MovieUiModel(
    id = id,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    posterPath = posterPath
)