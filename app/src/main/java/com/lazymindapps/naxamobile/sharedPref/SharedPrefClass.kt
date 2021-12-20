package com.lazymindapps.naxamobile.sharedPref

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.lazymindapps.naxamobile.utils.Constants

class SharedPrefClass(context: Context) {
    private val sharedPrefFile = "naxasharedpreference"

  var  sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPrefFile,MODE_PRIVATE)

    fun saveToken(result: String) {
        val edit = sharedPreferences.edit()
        edit.putString(Constants.TOKEN,result).apply()

    }

    fun getToken():String{
        val sharedToken = sharedPreferences.getString(Constants.TOKEN,"0")
        return sharedToken.toString()
    }



}