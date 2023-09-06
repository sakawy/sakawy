package com.itssvkv.loginsignup.data.local.repository

import android.content.Context
import com.itssvkv.loginsignup.utils.sharedPref.SharedPref
import javax.inject.Inject

class SplashRepo @Inject constructor() {

     fun getFromPref(context: Context, key: String, defValue: Any): Any =
        SharedPref.getFromPref(context, key, defValue)

     fun saveToPref(context: Context, key: String, value: Any) =
        SharedPref.saveToPref(context, key, value)
}