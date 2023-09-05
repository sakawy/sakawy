package com.itssvkv.loginsignup.utils.resultwrappar

import android.util.Log
import com.google.gson.Gson
import com.itssvkv.loginsignup.responses.errormodel.ErrorModel
import com.itssvkv.loginsignup.utils.common.Common.TAG
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

sealed class CallResult<out T> {
    data class CallSuccess<T>(val data: T) : CallResult<T>()
    data class CallFailure(val msg: String, val code: Int) : CallResult<Nothing>()
}

sealed class CallState {
    object LoadingState : CallState()
    object EmptyState : CallState()
    data class SuccessState<T>(val data: T) : CallState()
    data class FailureState(val msg: String? = null, val code: Int? = null) : CallState()
}

suspend fun <T> safeApiCall(apiCall: suspend () -> T): CallResult<T> {
    return try {
        CallResult.CallSuccess(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is SocketTimeoutException -> CallResult.CallFailure("Time is out", -1)
            is IOException -> CallResult.CallFailure("Check the internet", -2)
            is HttpException -> {
                val failure = getError(throwable)

                CallResult.CallFailure(failure.message, -3)
            }

            else -> {
                CallResult.CallFailure(throwable.message.toString(), -4)
            }
        }
    }
}

fun getError(exception: HttpException): ErrorModel {
    Log.d(TAG, "safeApiCall: ${exception.response()?.errorBody()}")

    return Gson().fromJson(exception.response()?.errorBody()?.string(), ErrorModel::class.java)

}