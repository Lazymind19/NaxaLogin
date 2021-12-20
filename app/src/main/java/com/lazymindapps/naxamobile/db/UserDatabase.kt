package com.lazymindapps.naxamobile.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lazymindapps.naxamobile.db.dao.UserDao
import com.lazymindapps.naxamobile.model.UserModel
import com.lazymindapps.naxamobile.model.models.UserData

@Database(entities = [UserData::class],version = 2)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao():UserDao

    companion object{
        private var INSTANCE:UserDatabase?=null

        fun createDatabase(context: Context):UserDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}