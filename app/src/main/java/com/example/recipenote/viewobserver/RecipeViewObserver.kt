package com.example.recipenote.viewobserver

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.recipenote.model.RecipeModel

class RecipeViewObserver() :BaseObservable() {
    private  var mModel :RecipeModel ? = null

    @Bindable
    fun getRecipeName()=mModel?.recipe?:""

    @Bindable
    fun getIngredient()=mModel?.Ingredients?:""

    @Bindable
    fun getHowToMake()= mModel?.howToMake?:""


    fun setData(data :RecipeModel)
    {
        mModel =data
        Log.i("Tag","mModel :$mModel")
        notifyChange()
    }
}