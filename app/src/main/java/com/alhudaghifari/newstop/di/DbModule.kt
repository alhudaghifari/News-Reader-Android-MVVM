package com.alhudaghifari.newstop.di

import android.app.Application
import androidx.room.Room
import com.alhudaghifari.newstop.data.local.room.AppDao
import com.alhudaghifari.newstop.data.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DbModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Application): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "App-db-newstop"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideNewsDao(database: AppDatabase): AppDao = database.appDao()
}