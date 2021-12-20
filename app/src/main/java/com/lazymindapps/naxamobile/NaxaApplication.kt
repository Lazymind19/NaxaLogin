package com.lazymindapps.naxamobile

import android.app.Application
import com.lazymindapps.naxamobile.db.UserDatabase
import com.lazymindapps.naxamobile.network.ResReqService
import com.lazymindapps.naxamobile.network.RetrofitHelper
import com.lazymindapps.naxamobile.repo.NaxaRepository

class NaxaApplication:Application() {
    val database by  lazy {
        UserDatabase.createDatabase(this)

    }
    val retrofit by lazy {
        RetrofitHelper.getRetrofitUser().create(ResReqService::class.java)
        RetrofitHelper.getRetrofitPost().create(ResReqService::class.java)
    }

    val repo by lazy {
        NaxaRepository(database,retrofit)
    }

}