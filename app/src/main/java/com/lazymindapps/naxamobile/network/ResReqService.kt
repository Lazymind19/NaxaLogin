package com.lazymindapps.naxamobile.network

import com.lazymindapps.naxamobile.model.UserModel
import com.lazymindapps.naxamobile.model.models.UserListData
import com.lazymindapps.naxamobile.model.models.UserLogin
import com.lazymindapps.naxamobile.model.models.UserLoginToken
import retrofit2.Response
import retrofit2.http.*


interface ResReqService {

    @GET("api/users?page=1")
   // suspend fun getUsers() : Response<UserModel>
    suspend fun getUsers() : Response<UserListData>

    @Headers("Content-Type:application/json")
    @POST("api/login")
    suspend fun loginUser(@Body userLogin: UserLogin):Response<UserLoginToken>



    @GET("")
    suspend fun getPosts(): Response<UserModel>

}