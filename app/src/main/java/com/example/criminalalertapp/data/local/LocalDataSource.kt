package com.example.criminalalertapp.data.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllCrimes(): Flow<List<CrimeEntity>>
    suspend fun insertCrime(crimeEntities: List<CrimeEntity>)
}