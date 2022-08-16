package com.himanskdevstuff.gnv_android_sdk.domain.use_case.nftList

import com.himanskdevstuff.gnv_android_sdk.domain.repository.GoNftVerseRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NftListUseCaseImpl @Inject constructor(private val goNftVerseRepository: GoNftVerseRepository) :
    NftListUseCase {
    override suspend fun invoke(param: NftListParam) = goNftVerseRepository.fetchNftList(param.token, param.marketPlaceId)
}