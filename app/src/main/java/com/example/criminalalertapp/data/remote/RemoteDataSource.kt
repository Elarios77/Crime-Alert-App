package com.example.criminalalertapp.data.remote

import com.example.criminalalertapp.data.remote.dto.CrimeDto

interface RemoteDataSource {
    suspend fun getCrimeStats(date: String?, lat: Double, lng: Double): List<CrimeDto>
}