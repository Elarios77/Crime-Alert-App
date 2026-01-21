package com.example.criminalalertapp.domain.model

data class CrimeItem(
    val id: Long,
    val category: String,
    val month: String,
    val latitude: Double,
    val longitude: Double,
    val streetName: String
)
