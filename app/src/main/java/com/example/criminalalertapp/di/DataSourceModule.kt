package com.example.criminalalertapp.di

import com.example.criminalalertapp.data.local.LocalDataSource
import com.example.criminalalertapp.data.remote.RemoteDataSource
import com.example.criminalalertapp.framework.datasource.LocalDataSourceImpl
import com.example.criminalalertapp.framework.datasource.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource
}