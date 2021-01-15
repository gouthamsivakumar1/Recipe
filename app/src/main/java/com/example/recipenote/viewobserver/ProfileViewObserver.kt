package com.example.recipenote.viewobserver

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.recipenote.model.Login

class ProfileViewObserver:BaseObservable() {

    private  var mLogin : Login? = null

    @Bindable
    fun getUserName() = mLogin?.username?:""


    fun setLogin(data:Login)
    {
        mLogin = data
        notifyChange()
    }
}