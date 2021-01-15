package com.example.recipenote.Repository

import androidx.room.Dao
import com.example.recipenote.model.RecipeModel
import com.example.recipenote.repository.db.api.AppDatabase


@Dao
class RecipeRepository (    private val mAppDatabase: AppDatabase)
{
    /***
     * get List
     */
    fun  getRecipeList()=mAppDatabase.recipeDao().getAlphabetizedWords()

    /**
     * set Item
     */
    fun setItemList(Recipe :RecipeModel)=mAppDatabase.recipeDao().insert(Recipe)

    /**
     * delete Receipt
     */
    fun deleteRecipe(recipe:String) =mAppDatabase.recipeDao().deleteRecipe(recipe)

    /**
     * get recipe from dao
     */
    fun getRecipe(recipe: String)=mAppDatabase.recipeDao().getRecipe(recipe)
}