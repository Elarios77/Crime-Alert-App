package com.example.criminalalertapp.data.repository

import android.util.Log.e
import com.example.criminalalertapp.data.local.LocalDataSource
import com.example.criminalalertapp.data.mapper.CrimeDtoMapper
import com.example.criminalalertapp.data.mapper.CrimeEntityMapper
import com.example.criminalalertapp.data.remote.RemoteDataSource
import com.example.criminalalertapp.domain.model.CrimeItem
import com.example.criminalalertapp.domain.repository.CrimeRepository
import com.example.criminalalertapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CrimeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val dtoMapper: CrimeDtoMapper,
    private val entityMapper: CrimeEntityMapper
) : CrimeRepository {

    override fun getCrimes(
        date: String?,
        lat: Double,
        lng: Double
    ): Flow<Resource<List<CrimeItem>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val remoteCrimes = remoteDataSource.getCrimeStats(date, lat, lng)
                val crimes = remoteCrimes.map { dtoMapper(it) }
                localDataSource.insertCrime(crimes.map { entityMapper(it) })
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Could not load data ${e.localizedMessage}"))
            } finally {
                emit(Resource.Loading(false))
            }

            val localFlow = localDataSource.getAllCrimes()
            emitAll(
                localFlow.map { entities ->
                    val domainCrimeItems = entities.map { entityMapper(it) }
                    Resource.Success(domainCrimeItems)
                }
            )
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