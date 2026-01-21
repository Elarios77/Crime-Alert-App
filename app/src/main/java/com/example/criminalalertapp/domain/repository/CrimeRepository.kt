package com.example.criminalalertapp.domain.repository

import com.example.criminalalertapp.domain.model.CrimeItem
import com.example.criminalalertapp.util.Resource
import kotlinx.coroutines.flow.Flow
interface CrimeRepository {
    fun getCrimes(
        date: String? = null,
        lat: Double,
        lng: Double
    ): Flow<Resource<List<CrimeItem>>>
}