package com.example.criminalalertapp.data.repository

import com.example.criminalalertapp.data.local.LocalDataSource
import com.example.criminalalertapp.data.mapper.CrimeDtoMapper
import com.example.criminalalertapp.data.mapper.CrimeEntityMapper
import com.example.criminalalertapp.data.remote.RemoteDataSource
import com.example.criminalalertapp.domain.model.CrimeItem
import com.example.criminalalertapp.domain.repository.CrimeRepository
import com.example.criminalalertapp.util.Resource
import javax.inject.Inject
import kotlin.collections.map

class CrimeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val dtoMapper: CrimeDtoMapper,
    private val entityMapper: CrimeEntityMapper
) : CrimeRepository {

    override suspend fun getCrimes(date: String?, lat: Double, lng: Double): Result<List<CrimeItem>> {
        return try {
            val response = remoteDataSource.getCrimeStats(date, lat, lng)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val mappedCrimes = body.map { dtoMapper(it) }
                    Result.success(mappedCrimes)
                } else {
                    Result.failure(Exception("Response body was null"))
                }
            } else {
                Result.failure(Exception("Server error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun reportCrime(crime: CrimeItem): Resource<Unit> {
        return try {
            val crimeEntity = entityMapper(crime)
            localDataSource.saveUserCrime(crimeEntity)
            Resource.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = "Could not save crime ${e.localizedMessage}")
        }
    }
}