package com.example.criminalalertapp.data.remote

import com.example.criminalalertapp.data.remote.dto.CrimeDto
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getCrimeStats(date: String?, lat: Double, lng: Double): Response<List<CrimeDto>>
}