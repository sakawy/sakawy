package com.itssvkv.loginsignup.responses.signupresponse

data class SignUpResponse(
    val access_token: String,
    val cart_count: Int,
    val expires_at: String,
    val notifications: Int,
    val status: String,
    val token_type: String,
    val user: User
) {
    data class User(
        val active: Int,
        val address_id: Any,
        val country_id: Int,
        val created_at: String,
        val deleted_at: Any,
        val device_type: String,
        val email: Any,
        val email_verified_at: String,
        val id: Int,
        val image: Any,
        val is_admin: Int,
        val locale: String,
        val name: String,
        val phone: String,
        val phone_code: String,
        val provider: Any,
        val provider_id: Any,
        val type: String,
        val updated_at: String,
        val vip: Int,
        val wallet: Any
    )
}