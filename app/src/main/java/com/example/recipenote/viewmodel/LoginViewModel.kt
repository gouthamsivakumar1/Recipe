package com.example.recipenote.viewmodel

import androidx.lifecycle.ViewModel
import com.example.recipenote.Repository.LoginRepository

class LoginViewModel (    private val mLoginRepository: LoginRepository):ViewModel()
{

    fun setUserName(username:String,password:String) =mLoginRepository.setLogin(username,password)

    fun getList() =mLoginRepository.getList()

    fun deleteAccount(){
        mLoginRepository.deleteAccount()
        mLoginRepository.deleteRecipeData()
    }
}