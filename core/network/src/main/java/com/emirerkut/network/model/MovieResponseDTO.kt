package com.emirerkut.network.model

import com.google.gson.annotations.SerializedName

data class MovieResponseDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("original_title")
    val original_title: String?,
    @SerializedName("original_language")
    val original_language: String?
)