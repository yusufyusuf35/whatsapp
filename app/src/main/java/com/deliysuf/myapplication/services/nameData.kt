package com.deliysuf.myapplication.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.deliysuf.myapplication.model.user

@Dao
interface nameData {
    @Insert
    suspend fun insertAll(set: Set<user>)
    @Query("SELECT * FROM USER WHERE name IN (:name)")
    suspend fun takeUser(name:String):user
    @Query("SELECT * FROM USER ")
    suspend fun getAll():List<user>
    @Query("DELETE FROM USER WHERE name IN (:Name)")
    suspend fun delete(Name:String)
    @Query("DELETE FROM USER")
    suspend fun allDelete()
}