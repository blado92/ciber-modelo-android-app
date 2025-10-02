package com.cibermodelo.databasemanager.di

import android.content.Context
import androidx.room.Room
import com.cibermodelo.databasemanager.CiberModeloDatabase
import com.cibermodelo.databasemanager.daos.UserDao
import com.cibermodelo.databasemanager.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CiberModeloDatabase {
        return Room.databaseBuilder(
            context,
            CiberModeloDatabase::class.java,
            CiberModeloDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: CiberModeloDatabase): UserDao =
        database.userDao()

}