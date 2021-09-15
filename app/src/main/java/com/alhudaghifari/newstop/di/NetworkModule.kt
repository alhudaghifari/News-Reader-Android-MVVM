package com.alhudaghifari.newstop.di

import com.alhudaghifari.newstop.api.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideNewsService(): NewsService = NewsService.create()
}