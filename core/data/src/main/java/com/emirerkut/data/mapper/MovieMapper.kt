package com.emirerkut.data.mapper

import com.emirerkut.model.Movie
import com.emirerkut.model.Genre
import com.emirerkut.model.SpokenLanguage
import com.emirerkut.model.Cast
import com.emirerkut.network.model.MovieDTO
import com.emirerkut.network.model.MovieResponseDTO

fun MovieDTO.toMovie() = Movie(
    id = id ?: -1,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    posterPath = posterPath,
    overview = overview,
    releaseDate = releaseDate,
    runtime = runtime,
    genres = genres?.map { genreDTO ->
        Genre(
            id = genreDTO.id ?: -1,
            name = genreDTO.name ?: ""
        )
    },
    spokenLanguages = spokenLanguages?.map { languageDTO ->
        SpokenLanguage(
            englishName = languageDTO.englishName,
            iso6391 = languageDTO.iso6391,
            name = languageDTO.name
        )
    },
    voteAverage = voteAverage,
    voteCount = voteCount,
    cast = credits?.cast?.take(10)?.map { castDTO ->
        Cast(
            id = castDTO.id ?: -1,
            name = castDTO.name,
            character = castDTO.character,
            profilePath = castDTO.profilePath
        )
    }
)

fun MovieResponseDTO.toMovieList(): List<Movie> {
    return results?.map { it.toMovie() } ?: emptyList()
}