package com.example.criminalalertapp.framework.datasource

import com.example.criminalalertapp.data.local.CrimeDao
import com.example.criminalalertapp.data.local.CrimeEntity
import com.example.criminalalertapp.data.local.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: CrimeDao
): LocalDataSource {

    override fun getAllCrimes(): Flow<List<CrimeEntity>> {
        return dao.getAllCrimes()
    }

    override suspend fun insertCrime(crimeEntities: List<CrimeEntity>) {
        dao.insertCrime(crimeEntities)
    }
}