package com.itssvkv.loginsignup.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.itssvkv.loginsignup.R
import com.itssvkv.loginsignup.ui.auth.AuthScreen
import com.itssvkv.loginsignup.ui.home.HomeScreen
import com.itssvkv.loginsignup.ui.onboarding.OnBoarding
import com.itssvkv.loginsignup.utils.sharedPref.SharedPref.getFromPref
import com.itssvkv.loginsignup.utils.sharedPref.SharedPref.saveToPref
import com.itssvkv.loginsignup.utils.sharedPref.SharedPrefKeys.IS_FIRST_TIME
import com.itssvkv.loginsignup.utils.sharedPref.SharedPrefKeys.LOGIN_DONE
import com.itssvkv.loginsignup.utils.sharedPref.SharedPrefKeys.REMEMBER_ME
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val isFirstTime = getFromPref(this, IS_FIRST_TIME, true) as Boolean
        val rememberMeChecked = getFromPref(this, REMEMBER_ME, false) as Boolean
        val user = getFromPref(this, LOGIN_DONE, "") as String

        Handler(Looper.getMainLooper()).postDelayed({
            if (isFirstTime) {
                saveToPref(this, IS_FIRST_TIME, false)
                startActivity(Intent(this, OnBoarding::class.java))
                finish()
            }else{
                if (user.isEmpty()){
                    startActivity(Intent(this, AuthScreen::class.java))
                    finish()
                }else if (user == "user"){
                    if (rememberMeChecked){
                        startActivity(Intent(this, HomeScreen::class.java))
                        finish()
                    }else{
                        startActivity(Intent(this, AuthScreen::class.java))
                        finish()
                    }
                }else{
                    startActivity(Intent(this, HomeScreen::class.java))
                    finish()
                }
            }
        }, 500L)
    }
}
