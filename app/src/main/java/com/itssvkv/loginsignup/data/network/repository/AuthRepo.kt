package com.itssvkv.loginsignup.data.network.repository

import com.itssvkv.loginsignup.data.network.ApiCalls
import com.itssvkv.loginsignup.utils.resultwrappar.safeApiCall
import javax.inject.Inject

class AuthRepo @Inject constructor(private val apiCalls: ApiCalls) {
    suspend fun login(
        phone: String,
        password: String,
        imei: String,
        token: String,
        deviceType: String
    ) =
        safeApiCall { apiCalls.login(phone, password, imei, token, deviceType) }

    suspend fun singUp(
        name: String,
        phone: String,
        password: String,
        confirmPassword: String,
        imei: String,
        token: String,
        deviceType: String
    ) = safeApiCall {
        apiCalls.signUp(
            name,
            phone,
            password,
            confirmPassword,
            imei,
            token,
            deviceType
        )
    }

}