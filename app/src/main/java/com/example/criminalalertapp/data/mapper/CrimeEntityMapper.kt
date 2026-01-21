package com.example.criminalalertapp.data.mapper

import com.example.criminalalertapp.data.local.CrimeEntity
import com.example.criminalalertapp.domain.model.CrimeItem
import javax.inject.Inject

class CrimeEntityMapper @Inject constructor() {
    operator fun invoke(entity: CrimeEntity): CrimeItem {
        return CrimeItem(
            id = entity.id,
            category = entity.category,
            month = entity.month,
            latitude = entity.latitude,
            longitude = entity.longitude,
            streetName = entity.streetName
        )
    }

    //ReverseMapping
    operator fun invoke(item: CrimeItem): CrimeEntity{
        return CrimeEntity(
            id = item.id,
            category = item.category,
            month = item.month,
            streetName = item.streetName,
            latitude = item.latitude,
            longitude = item.longitude
        )
    }
}