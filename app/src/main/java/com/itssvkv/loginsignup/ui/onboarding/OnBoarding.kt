package com.itssvkv.loginsignup.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.itssvkv.loginsignup.R
import com.itssvkv.loginsignup.databinding.ActivityOnBoardingBinding
import com.itssvkv.loginsignup.ui.auth.AuthScreen

class OnBoarding : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.onBoardingStartBtn.setOnClickListener {
            startActivity(Intent(this, AuthScreen::class.java))
            finish()

        }
    }
}