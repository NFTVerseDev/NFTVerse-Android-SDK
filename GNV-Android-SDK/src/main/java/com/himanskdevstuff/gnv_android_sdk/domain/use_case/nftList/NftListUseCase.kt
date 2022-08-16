package com.himanskdevstuff.gnv_android_sdk.domain.use_case.nftList

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.himanskdevstuff.gnv_android_sdk.domain.model.NftItem

interface NftListUseCase {
    suspend fun invoke(param: NftListParam): LiveData<PagingData<NftItem>>
}