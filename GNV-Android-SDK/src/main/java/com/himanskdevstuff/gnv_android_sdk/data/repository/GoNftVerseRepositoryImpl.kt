package com.himanskdevstuff.gnv_android_sdk.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.himanskdevstuff.gnv_android_sdk.data.pagingSources.NftListPagingSource
import com.himanskdevstuff.gnv_android_sdk.data.remote.GoNftVerseApi
import com.himanskdevstuff.gnv_android_sdk.data.remote.dto.NtfList.NftListDto
import com.himanskdevstuff.gnv_android_sdk.domain.model.NftItem
import com.himanskdevstuff.gnv_android_sdk.domain.repository.GoNftVerseRepository
import com.test.data.common.utils.Connectivity
import javax.inject.Inject

class GoNftVerseRepositoryImpl @Inject constructor(
    private val goNftVerseApi: GoNftVerseApi,
    private val connectivity: Connectivity
) : GoNftVerseRepository{

    override suspend fun fetchNftList(token: String, marketPlaceId : Int): LiveData<PagingData<NftItem>> {
        if(connectivity.hasNetworkAccess()){
            return Pager(
                config = PagingConfig(
                    pageSize = 1,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    NftListPagingSource(
                        goNftVerseApi = goNftVerseApi,
                        token = token,
                        marketPlaceId = marketPlaceId
                    )
                }
            ).liveData
        }
        return liveData {}
    }

}