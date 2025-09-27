package com.emirerkut.model

data class Movie(
    val id: Int,
    val originalTitle: String?,
    val originalLanguage: String?,
    val posterPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val runtime: Int?,
    val genres: List<Genre>?,
    val spokenLanguages: List<SpokenLanguage>?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val cast: List<Cast>?
)

data class Genre(
    val id: Int,
    val name: String
)

data class SpokenLanguage(
    val englishName: String?,
    val iso6391: String?,
    val name: String?
)

data class Cast(
    val id: Int,
    val name: String?,
    val character: String?,
    val profilePath: String?
)