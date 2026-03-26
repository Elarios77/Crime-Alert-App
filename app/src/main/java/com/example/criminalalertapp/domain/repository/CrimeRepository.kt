package com.example.criminalalertapp.domain.repository

import com.example.criminalalertapp.domain.model.CrimeItem
import com.example.criminalalertapp.util.Resource

interface CrimeRepository {
    suspend fun getCrimes(date: String? = null, lat: Double, lng: Double): Result<List<CrimeItem>>

    suspend fun reportCrime(crime: CrimeItem): Resource<Unit>
}