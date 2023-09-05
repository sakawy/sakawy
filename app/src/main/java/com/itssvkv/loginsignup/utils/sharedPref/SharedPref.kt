package com.itssvkv.loginsignup.utils.sharedPref

import android.content.Context
import android.util.Log
import com.itssvkv.loginsignup.utils.common.Common.TAG
import java.lang.ref.WeakReference

object SharedPref {

    fun saveToPref(context: Context, key: String, value: Any) {
        val contextWeakReference = WeakReference(context)
        if (contextWeakReference.get() != null) {
            val pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                contextWeakReference.get()!!
            )
            val editor = pref.edit()
            when (value) {
                is Int -> {
                    editor.putInt(key, value.toInt())
                }

                is String -> {
                    editor.putString(key, value.toString())
                }

                is Float -> {
                    editor.putFloat(key, value.toFloat())
                }
                is Boolean->{
                    editor.putBoolean(key,value.toString().toBoolean())
                }
                is Long->{
                    editor.putLong(key, value.toLong())
                }
            }
            editor.apply()
        }
    }

    fun getFromPref(context: Context, key: String, defValue:Any):Any{
        val contextWeakReference = WeakReference(context)
        if (contextWeakReference.get()!=null){
            val pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(contextWeakReference.get()!!)
            try {
                when(defValue){
                    is Int ->{
                      return  pref.getInt(key, defValue.toInt())
                    }
                    is String ->{
                        return pref.getString(key, defValue.toString()).toString()
                    }
                    is Float ->{
                        return pref.getFloat(key, defValue.toFloat())
                    }
                    is Long ->{
                        return pref.getLong(key, defValue.toLong())
                    }
                    is Boolean->{
                        return pref.getBoolean(key, defValue.toString().toBoolean())
                    }
                }

            }catch (e:Exception){
                Log.d(TAG, "getFromPref: ${e.message}")
                return defValue
            }
        }
        return defValue
    }
}