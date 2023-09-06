package com.itssvkv.loginsignup.ui.splash

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itssvkv.loginsignup.data.local.repository.SplashRepo
import com.itssvkv.loginsignup.utils.sharedPref.SharedPrefKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repo: SplashRepo,
) : ViewModel() {

    private val _isFirstTimeLiveData = MutableLiveData<Boolean>()
    val isFirstTimeLiveData = _isFirstTimeLiveData

    private val _userIsEmptyLiveData = MutableLiveData<Int>()
    val userIsEmptyLiveData = _userIsEmptyLiveData

    private val _isRememberMeLiveData = MutableLiveData<Boolean>()
    val isRememberMeLiveData = _isRememberMeLiveData
    fun init(context: Context) {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModelScope.launch {
                val isFirstTime =
                    repo.getFromPref(context, SharedPrefKeys.IS_FIRST_TIME, true) as Boolean
                val rememberMeChecked =
                    repo.getFromPref(context, SharedPrefKeys.REMEMBER_ME, false) as Boolean
                val user = repo.getFromPref(context, SharedPrefKeys.LOGIN_DONE, "") as String

                if (isFirstTime) {
                    viewModelScope.launch {
                        repo.saveToPref(context, SharedPrefKeys.IS_FIRST_TIME, false)
                        _isFirstTimeLiveData.value = true
                    }
                } else {
                    _isFirstTimeLiveData.value = false
                    if (user.isEmpty()) {
                        _userIsEmptyLiveData.value = 0

                    } else if (user == "user") {
                        _userIsEmptyLiveData.value = 1
                        _isRememberMeLiveData.value = rememberMeChecked
                    } else {
                        _userIsEmptyLiveData.value = 2
                    }
                }
            }
        }, 500L)
    }
}

