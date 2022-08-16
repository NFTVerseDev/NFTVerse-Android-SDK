package com.himanskdevstuff.gnv_android_sdk.di

import com.himanskdevstuff.gnv_android_sdk.domain.repository.GoNftVerseRepository
import com.himanskdevstuff.gnv_android_sdk.domain.use_case.nftList.NftListUseCase
import com.himanskdevstuff.gnv_android_sdk.domain.use_case.nftList.NftListUseCaseImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module

object InteractionModule {

    @Provides
    fun providesNftListUseCase( goNftVerseRepository : GoNftVerseRepository): NftListUseCase =
        NftListUseCaseImpl(goNftVerseRepository)

}