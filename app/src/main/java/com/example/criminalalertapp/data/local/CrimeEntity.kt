package com.example.criminalalertapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crimes")
data class CrimeEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val category:String,
    val month:String,
    val streetName:String,
    val latitude: Double,
    val longitude: Double
)