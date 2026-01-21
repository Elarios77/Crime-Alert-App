package com.example.criminalalertapp.di

import android.content.Context
import androidx.room.Room
import com.example.criminalalertapp.framework.AppDatabase
import com.example.criminalalertapp.data.local.CrimeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context : Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            klass = AppDatabase::class.java,
            name = "crimes"
        ).fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    fun provideCrimeDao(database:AppDatabase): CrimeDao{
        return database.crimeDao()
    }
}