package com.itssvkv.loginsignup.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.itssvkv.loginsignup.R
import com.itssvkv.loginsignup.databinding.ActivityLoginBinding
import com.itssvkv.loginsignup.ui.auth.AuthViewModel
import com.itssvkv.loginsignup.ui.auth.signup.SignUpActivity
import com.itssvkv.loginsignup.ui.home.HomeScreen
import com.itssvkv.loginsignup.utils.common.Common.TAG
import com.itssvkv.loginsignup.utils.resultwrappar.CallState
import com.itssvkv.loginsignup.utils.sharedPref.SharedPref.saveToPref
import com.itssvkv.loginsignup.utils.sharedPref.SharedPrefKeys.LOGIN_DONE
import com.itssvkv.loginsignup.utils.sharedPref.SharedPrefKeys.REMEMBER_ME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {

        binding.loginLoginBtn.setOnClickListener {
            val phone = binding.phoneNumEt.text.toString()
            val password = binding.passwordEt.text.toString()
            if (phone.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, "Invalid phone or password", Toast.LENGTH_SHORT).show()
            } else {

                viewModel.login(phone, password, "123", "123", "apple")
                observe()
                val rememberMe = binding.rememberMeCb.isChecked
                saveToPref(this, REMEMBER_ME, rememberMe)
                saveToPref(this, LOGIN_DONE, "user")
            }
        }

        binding.signUpTv.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }



    }
    private fun observe(){
        lifecycleScope.launch {
            viewModel.loginLiveData.collectLatest{
                when(it){
                    is CallState.SuccessState<*> -> {
                        startActivity(Intent(this@LoginActivity, HomeScreen::class.java))
                        finish()
                    }
                    is CallState.FailureState ->{
                        Log.d(TAG, "observe: ${it.msg}")
                        Toast.makeText(this@LoginActivity, it.msg, Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Log.d(TAG, "observe: sh333'")
                    }
                }
            }
        }
    }
}