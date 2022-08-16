package com.himanskdevstuff.gnv_android_sdk.domain.common


sealed class Result<out T : Any>
data class Success<out T : Any>(val data: T) : Result<T>()
data class Failure(val errorResponse: ErrorResponse) : Result<Nothing>()

class HttpError(val throwable: Throwable, val errorCode: Int = 0)

inline fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
  if (this is Success) action(data)
  return this
}

inline fun <T : Any> Result<T>.onFailure(action: (ErrorResponse) -> Unit) {
  if (this is Failure) action(errorResponse)
}