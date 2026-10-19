package com.example.data.di

import com.example.data.repository.MediaStoreRepositoryImpl
import com.example.data.repository.MemeStoreRepositoryImpl
import com.example.domain.repository.MediaStoreRepository
import com.example.domain.repository.MemeStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun binMediaStoreRepository(
        impl: MediaStoreRepositoryImpl
    ): MediaStoreRepository

    @Binds
    @Singleton
    abstract fun bindMemeRepository(
        impl: MemeStoreRepositoryImpl
    ): MemeStoreRepository
}