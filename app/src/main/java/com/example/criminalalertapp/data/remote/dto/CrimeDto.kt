package com.example.criminalalertapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CrimeDto(
    @field:Json(name = "id") val id: Long?,
    @field:Json(name = "category") val category: String?,
    @field:Json(name = "month") val month: String?,
    @field:Json(name = "location") val location: LocationDto?
)

@JsonClass(generateAdapter = true)
data class LocationDto(
    @field:Json(name = "latitude") val latitude: String?,
    @field:Json(name = "longitude") val longitude: String?,
    @field:Json(name = "street") val street: StreetDto?
)

@JsonClass(generateAdapter = true)
data class StreetDto(
    @field:Json(name = "name") val streetName: String?
)
