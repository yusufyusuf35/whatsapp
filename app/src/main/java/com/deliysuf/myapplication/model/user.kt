package com.deliysuf.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class user (@PrimaryKey(autoGenerate = true)var uuid:Int,
                 @ColumnInfo(name="Email")val Email:String?,
                 @ColumnInfo(name="password")val password:String?,
                 @ColumnInfo(name="name")val name:String?,
                 @ColumnInfo(name="surname")val surname:String?,
                 @ColumnInfo(name="Phone")val Phone:String?,
                 @ColumnInfo(name="UserId")val UserId:String?
                )