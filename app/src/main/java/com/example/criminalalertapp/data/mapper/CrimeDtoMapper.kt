package com.example.criminalalertapp.data.mapper

import com.example.criminalalertapp.data.remote.dto.CrimeDto
import com.example.criminalalertapp.domain.model.CrimeItem
import javax.inject.Inject

class CrimeDtoMapper @Inject constructor() {
    operator fun invoke(dto: CrimeDto?): CrimeItem {
        return CrimeItem(
            id = dto?.id ?: 0L,
            category = dto?.category ?: "No category",
            month = dto?.month ?: "",
            latitude = dto?.location?.latitude?.toDoubleOrNull() ?: 0.0,
            longitude = dto?.location?.longitude?.toDoubleOrNull() ?: 0.0,
            streetName = dto?.location?.street?.streetName ?: "Unknown street"
        )
    }
}
