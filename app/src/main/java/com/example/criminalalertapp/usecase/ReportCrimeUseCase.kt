package com.example.criminalalertapp.usecase

import com.example.criminalalertapp.domain.model.CrimeItem
import com.example.criminalalertapp.domain.repository.CrimeRepository
import com.example.criminalalertapp.util.Resource
import javax.inject.Inject

class ReportCrimeUseCase @Inject constructor(
    private val repository: CrimeRepository
) {
    suspend operator fun invoke(crime: CrimeItem): Resource<Unit> {
        return repository.reportCrime(crime)
    }
}