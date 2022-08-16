package com.himanskdevstuff.gnv_android_sdk.data.pagingSources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.himanskdevstuff.gnv_android_sdk.data.remote.GoNftVerseApi
import com.himanskdevstuff.gnv_android_sdk.domain.model.NftItem
import retrofit2.HttpException
import java.io.IOException

class NftListPagingSource(
    private val goNftVerseApi: GoNftVerseApi,
    private val token : String,
    private val marketPlaceId : Int
) : PagingSource<Int, NftItem>() {
    override fun getRefreshKey(state: PagingState<Int, NftItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NftItem> {
        val pageNo = params.key ?: 0
        return try {
            val response = goNftVerseApi.fetchNftList(marketPlaceId, pageNo, token)
            val it = response.body()
            val nftList = mutableListOf<NftItem>()
            if (it != null)
                for (item in it.content) {
                    val entity = NftItem(
                        item.blockchain,
                        item.blockchainNftId,
                        item.marketplaceId,
                        item.metaData,
                        item.nftId,
                        item.status,
                        item.transactionId,
                        item.userId
                    )
                    nftList.add(entity)
                }
            LoadResult.Page(
                data = nftList,
                prevKey = if (pageNo == 0) null else pageNo - 1,
                nextKey = if (nftList.isEmpty()) null else pageNo + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}