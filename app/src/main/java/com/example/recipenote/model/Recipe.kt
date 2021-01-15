package com.example.recipenote.model

import com.google.gson.annotations.SerializedName

data class Recipe (
    @SerializedName("categories") val categories : List<Categories>
)
data class Categories (

    @SerializedName("idCategory") val idCategory : Int,
    @SerializedName("strCategory") val strCategory : String,
    @SerializedName("strCategoryThumb") val strCategoryThumb : String,
    @SerializedName("strCategoryDescription") val strCategoryDescription : String
)