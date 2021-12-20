package com.lazymindapps.naxamobile.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lazymindapps.naxamobile.model.UserModel
import com.lazymindapps.naxamobile.model.models.UserData

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userData: UserData)

    @Query("Select * from tbl_user")
    fun getAllUser():LiveData<List<UserModel>>

    //searchquery
    @Query("SELECT * FROM tbl_user WHERE first_name like :searchQuery")
    fun searchFromDatabase(searchQuery: String) : LiveData<List<UserModel>>
}