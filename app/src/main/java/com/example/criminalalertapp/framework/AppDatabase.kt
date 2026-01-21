package com.example.criminalalertapp.framework

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.criminalalertapp.data.local.CrimeDao
import com.example.criminalalertapp.data.local.CrimeEntity

@Database(
    entities = [CrimeEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase(){
    abstract fun crimeDao(): CrimeDao
}