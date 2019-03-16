package com.adlubagusi.myfriendapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyFriend(
    @PrimaryKey(autoGenerate = true)
    val friendId: Int? = null,
    val name: String,
    val gender: String,
    val email: String,
    val telp: String,
    val address: String
)