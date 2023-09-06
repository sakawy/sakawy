package com.itssvkv.loginsignup.ui.auth.login

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
class LoginViewModel @Inject constructor(private val repo: AuthRepo) : ViewModel() {
    private val _loginLiveData = MutableStateFlow<CallState>(CallState.EmptyState)
    val loginLiveData = _loginLiveData.asStateFlow()


    fun login(
        phone: String,
        password: String,
        imei: String,
        token: String,
        deviceType: String
    ) {
        viewModelScope.launch {
            _loginLiveData.value = CallState.LoadingState
            when (val response = repo.login(phone, password, imei, token, deviceType)) {
                is CallResult.CallSuccess -> {
                    _loginLiveData.value = CallState.SuccessState(response.data)
                }

                is CallResult.CallFailure -> {
                    _loginLiveData.value = CallState.FailureState(response.msg, response.code)
                }
            }
        }
    }
}