package com.example.recipenote.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "RecipeModel")
data class RecipeModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")var id:Int,
    @ColumnInfo(name = "Recipe")var recipe:String,
    @ColumnInfo(name = "Ingredients")var Ingredients:String,
    @ColumnInfo(name = "howToMake")var howToMake:String,
    @ColumnInfo(name = "picValue")var picValue:ByteArray)
