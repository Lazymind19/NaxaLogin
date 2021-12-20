package com.lazymindapps.naxamobile.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_user")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val avatar: String,
    val email: String,
    val first_name: String,

    val last_name: String
)