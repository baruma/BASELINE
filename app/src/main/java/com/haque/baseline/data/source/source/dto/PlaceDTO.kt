package com.haque.baseline.data.source.source.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaceDTO(
    @field:Json(name = "name")
    val city: String,
    val latitude: Float,
    val longitude: Float,
    val country: String,
    val state: String
)