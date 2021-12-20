package com.lazymindapps.naxamobile.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_user")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val first_name: String,
    val last_name: String,
    val email: String,
    val avatar: String

)