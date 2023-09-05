package com.itssvkv.loginsignup.data.network

import com.itssvkv.loginsignup.responses.loginresponse.LoginResponse
import com.itssvkv.loginsignup.responses.signupresponse.SignUpResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiCalls {
    @Headers("Accept: application/json")
    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("imei") imei: String,
        @Field("token") token: String,
        @Field("device_type") deviceType: String

    ): LoginResponse


    @POST("register")
    @FormUrlEncoded
    suspend fun signUp(
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("confirm_password") confirmPassword: String,
        @Field("imei") imei: String,
        @Field("token") token: String,
        @Field("device_type") deviceType: String
    ): SignUpResponse
}