package com.lazymindapps.naxamobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lazymindapps.naxamobile.model.UserModel
import com.lazymindapps.naxamobile.model.models.UserData
import com.lazymindapps.naxamobile.model.models.UserListData
import com.lazymindapps.naxamobile.model.models.UserLogin
import com.lazymindapps.naxamobile.model.models.UserLoginToken
import com.lazymindapps.naxamobile.repo.NaxaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class NaxaViewModel(val repo:NaxaRepository):ViewModel() {

    lateinit var token: String


    init {
        viewModelScope.launch(Dispatchers.IO){
            repo.getUsersFromApi()
           // repo.getPostFromApi()
        }
    }

    val users : LiveData<UserListData>
    get() = repo.userApi

    val posts : LiveData<UserModel>
    get() = repo.postApi

    var allUserList : LiveData<List<UserModel>>?=null
    var allPostList: LiveData<List<UserModel>>?=null




    suspend fun insertUserInDb(userData: UserData) = repo.insertUser(userData)

    fun  getAllUserFromDb():LiveData<List<UserModel>> {
        allUserList = repo.getAllUser()
        return allUserList as LiveData<List<UserModel>>
    }

    fun searchFromDatabase(searchQuery:String) :LiveData<List<UserModel>> {

       allUserList = repo.searchFromDatabase(searchQuery)
        return allUserList as LiveData<List<UserModel>>

    }

//    suspend fun getUserLoginFromApi(userLogin: UserLogin) = repo.getUserLoginFromApi(userLogin)
    suspend fun login(userLogin: UserLogin) : String {
       val result = repo.loginUser(userLogin)
    token = null.toString()
    if (result.code() ==200){
        token = result.body()!!.token



    }
    return token

    }



}