package com.himanskdevstuff.gnv_android_sdk.di

import com.himanskdevstuff.gnv_android_sdk.data.remote.GoNftVerseApi
import com.himanskdevstuff.gnv_android_sdk.data.repository.GoNftVerseRepositoryImpl
import com.himanskdevstuff.gnv_android_sdk.domain.repository.GoNftVerseRepository
import com.test.data.common.utils.ConnectivityImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun providesMarketPlaceRepository(
        goNftVerseApi: GoNftVerseApi,
        connectivity: ConnectivityImpl
    ): GoNftVerseRepository =
        GoNftVerseRepositoryImpl(goNftVerseApi, connectivity)
}