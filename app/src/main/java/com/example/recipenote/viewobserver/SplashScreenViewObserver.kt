package com.example.recipenote.viewobserver

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SplashScreenViewObserver :BaseObservable(){

     private  var mHeading :String? = null
    @Bindable
    fun getHeading() = mHeading


    fun setData(data:String)
    {
        mHeading =data
        notifyChange()
    }
}