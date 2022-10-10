package com.deliysuf.myapplication.services

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deliysuf.myapplication.model.user

@Database(entities = [user::class], version = 1)
abstract class nameDataBase:RoomDatabase() {
    abstract  fun name():nameData
}