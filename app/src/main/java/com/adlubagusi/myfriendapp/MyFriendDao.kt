package com.adlubagusi.myfriendapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyFriendDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFriends(friend: MyFriend)

    @Query("SELECT * FROM MyFriend")
    fun getAllFriend(): LiveData<List<MyFriend>>
}