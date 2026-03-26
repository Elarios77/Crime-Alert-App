package com.example.criminalalertapp.usecase

import com.example.criminalalertapp.domain.model.CrimeItem
import com.example.criminalalertapp.domain.repository.CrimeRepository
import javax.inject.Inject

class GetCrimesUseCase @Inject constructor(
    private val repository: CrimeRepository
) {
    suspend operator fun invoke(date: String?, lat: Double, lng: Double): Result<List<CrimeItem>> {
        return repository.getCrimes(date, lat, lng)
    }
}