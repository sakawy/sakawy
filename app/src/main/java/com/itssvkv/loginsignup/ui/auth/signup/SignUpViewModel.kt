package com.itssvkv.loginsignup.ui.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itssvkv.loginsignup.data.network.repository.AuthRepo
import com.itssvkv.loginsignup.utils.resultwrappar.CallResult
import com.itssvkv.loginsignup.utils.resultwrappar.CallState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repo: AuthRepo
) : ViewModel() {

    private val _signUpStateFlow = MutableStateFlow<CallState>(CallState.EmptyState)
    val signUpStateFlow = _signUpStateFlow.asStateFlow()

    fun signUp(
        name: String,
        phone: String,
        password: String,
        confirmPassword: String,
        imei: String,
        token: String,
        deviceType: String
    ) {
        viewModelScope.launch {
            _signUpStateFlow.value = CallState.LoadingState
            when (val response =
                repo.singUp(name, phone, password, confirmPassword, imei, token, deviceType)) {
                is CallResult.CallSuccess -> {
                    _signUpStateFlow.value = CallState.SuccessState(response.data)
                }

                is CallResult.CallFailure -> {
                    _signUpStateFlow.value = CallState.FailureState(response.msg, response.code)
                }
            }
        }
    }

}