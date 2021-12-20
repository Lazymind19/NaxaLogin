package com.lazymindapps.naxamobile.network

import com.lazymindapps.naxamobile.utils.Constants.Companion.BASE_JSONPLACEHOLDER_URL
import com.lazymindapps.naxamobile.utils.Constants.Companion.BASE_REQRES_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofitUser(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_REQRES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }

    fun getRetrofitPost(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_JSONPLACEHOLDER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}