package com.example.recipenote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login")
data class Login(
    @PrimaryKey
    @ColumnInfo(name = "FirstName")var username:String,
    @ColumnInfo(name = "Password")var password:String
)