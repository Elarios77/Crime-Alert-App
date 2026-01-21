package com.example.criminalalertapp.data.remote


import com.example.criminalalertapp.data.remote.dto.CrimeDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PoliceApi {

    @GET("crimes-street/all-crime")
    suspend fun getCrimeStats(
        @Query("date") date: String? = null,
        @Query("lat") lat: Double,
        @Query("lng") lng: Double
    ) : List<CrimeDto>
}