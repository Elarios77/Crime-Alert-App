package com.example.criminalalertapp.framework.datasource

import com.example.criminalalertapp.data.remote.PoliceApi
import com.example.criminalalertapp.data.remote.RemoteDataSource
import com.example.criminalalertapp.data.remote.dto.CrimeDto
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: PoliceApi
) : RemoteDataSource {
    override suspend fun getCrimeStats(date: String?,lat: Double,lng: Double): Response<List<CrimeDto>> {
        return api.getCrimeStats(date, lat, lng)
    }
}