package com.itssvkv.loginsignup.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itssvkv.loginsignup.databinding.ActivityAuthScreenBinding
import com.itssvkv.loginsignup.ui.auth.login.LoginActivity
import com.itssvkv.loginsignup.ui.auth.signup.SignUpActivity

class AuthScreen : AppCompatActivity() {
    private lateinit var binding: ActivityAuthScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.signUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

    }
}