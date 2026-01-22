package com.example.criminalalertapp.usecase

import com.example.criminalalertapp.domain.model.CrimeItem
import com.example.criminalalertapp.domain.repository.CrimeRepository
import com.example.criminalalertapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCrimesUseCase @Inject constructor(
    private val repository: CrimeRepository
) {
    operator fun invoke(date:String? , lat:Double, lng:Double): Flow<Resource<List<CrimeItem>>> {
        return repository.getCrimes( date, lat, lng)
    }
}