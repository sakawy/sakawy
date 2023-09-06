package com.itssvkv.loginsignup.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.itssvkv.loginsignup.R
import com.itssvkv.loginsignup.ui.auth.AuthScreen
import com.itssvkv.loginsignup.ui.home.HomeScreen
import com.itssvkv.loginsignup.ui.onboarding.OnBoarding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        init()
    }

    private fun init() {
        viewModel.init(this@SplashScreen)
        observe()

    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.isFirstTimeLiveData.observe(this@SplashScreen) {
                when (it) {
                    true -> {
                        startActivity(Intent(this@SplashScreen, OnBoarding::class.java))
                        finish()
                    }

                    false -> {
                        viewModel.userIsEmptyLiveData.observe(this@SplashScreen) {
                            when (it) {
                                0 -> {
                                    startActivity(Intent(this@SplashScreen, AuthScreen::class.java))
                                    finish()
                                }

                                1 -> {
                                    viewModel.isRememberMeLiveData.observe(this@SplashScreen) {
                                        when (it) {
                                            true -> {
                                                startActivity(
                                                    Intent(
                                                        this@SplashScreen,
                                                        HomeScreen::class.java
                                                    )
                                                )
                                                finish()
                                            }

                                            false -> {
                                                startActivity(
                                                    Intent(
                                                        this@SplashScreen,
                                                        AuthScreen::class.java
                                                    )
                                                )
                                                finish()
                                            }
                                        }

                                    }
                                }

                                2 -> {
                                    startActivity(Intent(this@SplashScreen, HomeScreen::class.java))
                                    finish()
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
