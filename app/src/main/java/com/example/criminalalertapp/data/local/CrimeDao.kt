package com.example.criminalalertapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CrimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrime(crimeEntities:List<CrimeEntity>)

    @Query("SELECT * FROM crimes")
    fun getAllCrimes() : Flow<List<CrimeEntity>>
}