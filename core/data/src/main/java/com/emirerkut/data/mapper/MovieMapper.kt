package com.emirerkut.data.mapper

import com.emirerkut.model.Movie
import com.emirerkut.network.model.MovieDTO

fun MovieDTO.toMovie() = Movie(
    id = id ?: -1,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage
)