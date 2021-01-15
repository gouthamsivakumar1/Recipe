package com.example.recipenote.Repository

import com.example.recipenote.model.Login
import com.example.recipenote.repository.db.api.AppDatabase

class LoginRepository (    private val mAppDatabase: AppDatabase)
{
    /**
     *  setting Login
     */
    fun setLogin( username:String , password:String ) {
        mAppDatabase.loginDao().insert(Login(username,password))
    }

    /***
     * get List
     */
    fun  getList()=mAppDatabase.loginDao().getAlphabetizedWords()


    /**
     * delete Account
     */
    fun deleteAccount() =mAppDatabase.loginDao().deleteAll()

    /***
     * delete Recipe data
     */
    fun deleteRecipeData() =mAppDatabase.recipeDao().deleteAll()


}