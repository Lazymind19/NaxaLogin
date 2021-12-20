package com.lazymindapps.naxamobile.model.models

data class UserListData(
    val data: List<UserData>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)