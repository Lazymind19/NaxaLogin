package com.lazymindapps.naxamobile.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lazymindapps.naxamobile.db.UserDatabase
import com.lazymindapps.naxamobile.model.UserModel
import com.lazymindapps.naxamobile.model.models.UserData
import com.lazymindapps.naxamobile.model.models.UserListData
import com.lazymindapps.naxamobile.model.models.UserLogin
import com.lazymindapps.naxamobile.model.models.UserLoginToken
import com.lazymindapps.naxamobile.network.ResReqService
import retrofit2.Response

class NaxaRepository(private val db:UserDatabase, private val reqService: ResReqService) {
    //Fetch and other operation from network api
    private  val userList = MutableLiveData<UserListData>()
    private val postList = MutableLiveData<UserModel>()
    lateinit var token:String



    val userApi :LiveData<UserListData>
    get() = userList

    val postApi : LiveData<UserModel>
    get() = postList

    suspend fun getUsersFromApi(){
        val result = reqService.getUsers()
        if (result?.body()!=null){
            val userData = result.body()
            userList.postValue(userData!!)
            val user = result.body()
        }
    }

//    suspend fun getUserLoginFromApi(userLogin: UserLogin){
//       reqService.loginUser(userLogin)
//
//
//    }


    suspend fun getPostFromApi(){
        val result = reqService.getPosts()
        if (result?.body()!=null){
            postList.postValue(result.body())
            val post = result.body()
        }
    }


    //Fetch or operation from database

    suspend fun loginUser(userLogin: UserLogin): Response<UserLoginToken> = reqService.loginUser(userLogin)

    suspend fun insertUser(userData: UserData) = db.userDao().insertUser(userData)
     fun getAllUser():LiveData<List<UserModel>> = db.userDao().getAllUser()
    fun searchFromDatabase(searchQuery:String):LiveData<List<UserModel>> = db.userDao().searchFromDatabase(searchQuery)
}