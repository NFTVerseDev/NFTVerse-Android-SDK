package com.test.data.remote.base

import com.google.gson.GsonBuilder
import com.himanskdevstuff.gnv_android_sdk.domain.common.Failure
import com.himanskdevstuff.gnv_android_sdk.domain.common.Success
import com.himanskdevstuff.gnv_android_sdk.domain.common.ErrorData
import com.himanskdevstuff.gnv_android_sdk.domain.common.ErrorResponse
import com.himanskdevstuff.gnv_android_sdk.domain.common.Result
import retrofit2.Response
import java.io.IOException

abstract class NetworkResult<out T : Any> : DomainMapper<T>

interface DomainMapper<out T : Any> {
    fun mapToDomainModel(): T
}

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (ErrorResponse?) -> Unit) {
    val errorResponse =
            GsonBuilder().create().fromJson(errorBody()?.string(), ErrorResponse::class.java)
    if (!isSuccessful) errorBody()?.run { action(errorResponse) }
}

/**
 * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
 */

inline fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<T>.getData(
        cacheAction: (R) -> Unit,
        fetchFromCacheAction: () -> R
): com.himanskdevstuff.gnv_android_sdk.domain.common.Result<U> {
    try {
        onSuccess {
            val databaseEntity = it.mapToRoomEntity()
            cacheAction(databaseEntity)
            return Success(databaseEntity.mapToDomainModel())
        }
        onFailure {
            val cachedModel = fetchFromCacheAction()
            if (cachedModel != null) Success(cachedModel.mapToDomainModel()) else Failure(
                    ErrorResponse(
                            code = "Error",
                            message = "No Data in DB",
                            data = ErrorData(status = "")
                    )
            )
        }
        return Failure(
            ErrorResponse(
                code = "Error",
                message = "Network Error",
                data = ErrorData(status = "")
        )
        )
    } catch (e: IOException) {
        return Failure(
            ErrorResponse(
                code = "Error",
                message = "Network Error",
                data = ErrorData(status = "")
        )
        )
    }
}

/**
 * Use this when communicating only with the api service
 */
fun <T : DomainMapper<R>, R : Any> Response<T>.getData(): com.himanskdevstuff.gnv_android_sdk.domain.common.Result<R> {
    try {
        onSuccess { return Success(it.mapToDomainModel()) }
        onFailure { return Failure(it!!) }
        return Failure(
            ErrorResponse(
                code = "Error",
                message = "Network Error",
                data = ErrorData(status = "")
        )
        )
    } catch (e: IOException) {
        return Failure(
            ErrorResponse(
                code = "Error",
                message = "Network Error",
                data = ErrorData(status = "")
        )
        )
    }
}

/**
 * Use this function when you need to fetch list of data from the API
 */
fun <T : DomainMapper<R>, R : Any> Response<List<T>>.getDataAsList(): com.himanskdevstuff.gnv_android_sdk.domain.common.Result<List<R>> {
    try {
        onSuccess { responseList -> return Success(responseList.map { it.mapToDomainModel() }) }
        onFailure { return Failure(it!!) }
        return Failure(
            ErrorResponse(
                code = "Error",
                message = "Network Error",
                data = ErrorData(status = "")
        )
        )
    } catch (e: IOException) {
        return Failure(
            ErrorResponse(
                code = "Error",
                message = "Network Error",
                data = ErrorData(status = "")
        )
        )
    }
}

/**
 * Use this function when you need to fetch list of data from the API and cache it locally
 */
inline fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<List<T>>.getDataAsList(
        cacheAction: (List<R>) -> Unit,
        fetchFromCacheAction: () -> List<R>
): Result<List<U>> {
    try {
        onSuccess { responseList ->
            val listOfEntities = responseList.map { it.mapToRoomEntity() }
            cacheAction(listOfEntities)
            return Success(listOfEntities.map { it.mapToDomainModel() })
        }
        onFailure {
            val cachedList = fetchFromCacheAction()
            if (cachedList.isNotEmpty()) Success(cachedList.map { it.mapToDomainModel() })
            else Failure(
                ErrorResponse(
                    code = "Error",
                    message = "DB Error",
                    data = ErrorData(status = "")
            )
            )
        }
        return Failure(
            ErrorResponse(
                code = "Error",
                message = "Network Error",
                data = ErrorData(status = "")
        )
        )
    } catch (e: IOException) {
        return Failure(
            ErrorResponse(
                code = "Error",
                message = "Network Error",
                data = ErrorData(status = "")
        )
        )
    }
}
