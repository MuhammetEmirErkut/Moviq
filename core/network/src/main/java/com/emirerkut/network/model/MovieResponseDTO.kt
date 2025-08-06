package com.emirerkut.network.model

import com.google.gson.annotations.SerializedName

data class MovieResponseDTO(
    @SerializedName("results")
    val results: List<MovieDTO>?
)