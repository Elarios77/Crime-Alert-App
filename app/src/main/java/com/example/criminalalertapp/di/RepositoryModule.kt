package com.example.criminalalertapp.di

import com.example.criminalalertapp.data.repository.CrimeRepositoryImpl
import com.example.criminalalertapp.domain.repository.CrimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCrimeRepository(impl: CrimeRepositoryImpl): CrimeRepository = impl
}