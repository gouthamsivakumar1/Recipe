package com.example.recipenote.repository.db.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipenote.model.Login
import com.example.recipenote.model.RecipeModel

@Database(entities =[Login:: class,RecipeModel::class], version = 1,exportSchema = false)
abstract class AppDatabase :RoomDatabase() {

        abstract fun loginDao(): LoginDao
        abstract fun recipeDao():RecipeDao


}