package com.itssvkv.loginsignup.ui.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.itssvkv.loginsignup.R
import com.itssvkv.loginsignup.databinding.ActivitySignUpBinding
import com.itssvkv.loginsignup.ui.auth.AuthViewModel
import com.itssvkv.loginsignup.ui.auth.login.LoginActivity
import com.itssvkv.loginsignup.utils.common.Common
import com.itssvkv.loginsignup.utils.resultwrappar.CallState
import com.itssvkv.loginsignup.utils.sharedPref.SharedPref.saveToPref
import com.itssvkv.loginsignup.utils.sharedPref.SharedPrefKeys
import com.itssvkv.loginsignup.utils.sharedPref.SharedPrefKeys.LOGIN_DONE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.signUpSignUpBtn.setOnClickListener {
            val name = binding.signUpNameEt.text.toString()
            val phone = binding.signUpPhoneNumEt.text.toString()
            val password = binding.signUpPasswordEt.text.toString()
            val rePassword = binding.signUpRePasswordEt.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(this, "Name is require!", Toast.LENGTH_SHORT).show()
            } else if (phone.isEmpty()) {
                Toast.makeText(this, "Phone is require!", Toast.LENGTH_SHORT).show()

            } else if (password.isEmpty()) {
                Toast.makeText(this, "Password is require!", Toast.LENGTH_SHORT).show()

            } else if (rePassword.isEmpty()) {
                Toast.makeText(this, "Please, confirm the password!", Toast.LENGTH_SHORT).show()

            } else {
                if (password != rePassword) {
                    Toast.makeText(
                        this,
                        "password and confirm password doesn't match",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    viewModel.signUp(name, phone, password, rePassword, "123", "123", "apple")
                    observe()
                }
            }
        }

    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.signUpStateFlow.collectLatest {
                when (it) {
                    is CallState.SuccessState<*> -> {
                        Toast.makeText(
                            this@SignUpActivity,
                            "Sign up success please, login",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                        finish()

                    }

                    is CallState.FailureState -> {
                        Log.d(Common.TAG, "observe: ${it.msg}")
                        Toast.makeText(this@SignUpActivity, it.msg, Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Log.d(Common.TAG, "observe: sh333'")
                    }
                }
            }
        }
    }
}