package com.example.recipenote.viewmodel
import androidx.lifecycle.ViewModel
import com.example.recipenote.Repository.RecipeRepository
import com.example.recipenote.model.RecipeModel

class  RecipeViewModel(private  val mRepository: RecipeRepository):ViewModel()
{
    private  var mModel:RecipeModel? = null
    /**
     * fun  get Recipe List
     */
    fun  getRecipeList()=mRepository.getRecipeList()

    /**
     * funn  set Recipe List
     */
    fun setItemList(Recipe : RecipeModel)=mRepository.setItemList(Recipe)

    /**
     * set  Details of Recipe  From  Viewholder
     */
    fun setDetails(data:RecipeModel)
    {
        mModel =data
    }

    /**
     * get Details of Recipe
     */
    fun getDetails() =mModel

    /**
     * delete particular Recipe Item
     */
    fun deleteRecipe(recipe:String) =mRepository.deleteRecipe(recipe)

    /***
     * get recipe of a particular list
     */
    fun getRecipe(recipe: String)=mRepository.getRecipe(recipe)

}