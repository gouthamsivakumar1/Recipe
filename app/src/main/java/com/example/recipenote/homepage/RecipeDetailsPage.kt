package com.example.recipenote.homepage

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.api.load
import com.example.recipenote.R
import com.example.recipenote.Utils.setRxOnClickListener
import com.example.recipenote.databinding.FragmentRecipeDetailsPageBinding
import com.example.recipenote.model.RecipeModel
import com.example.recipenote.viewmodel.RecipeViewModel
import com.example.recipenote.viewobserver.RecipeViewObserver
import kotlinx.android.synthetic.main.fragment_recipe_details_page.*
import kotlinx.android.synthetic.main.recipe_list_item.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RecipeDetailsPage : Fragment() {
    private  val mViewModel:RecipeViewModel by sharedViewModel()
    private  var mViewObserver =RecipeViewObserver()
    private  var ReceipeName:String? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentRecipeDetailsPageBinding.inflate(inflater,container,false)
        binding.data = mViewObserver
        return  binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      loadData()
        loadButtonFunctionality()

   }

    private fun loadButtonFunctionality() {
        imDelete.setRxOnClickListener {
         deleteRecipe()
        }
    }

    /**
     * fun to delete particular receipe Item
     */
    private fun deleteRecipe() {
        GlobalScope.launch(Dispatchers.IO) {
           mViewModel.deleteRecipe(ReceipeName!!)
            findNavController().navigateUp()
        }


    }


    /**
     * fun to Load Data
     */
    private fun loadData() {
        mViewModel.getDetails()?.let{
            ReceipeName = it.recipe
            mViewObserver.setData(it)
            it.picValue?.let{
                val picvalue = BitmapFactory.decodeByteArray(it, 0, it.size)
                picvalue?.let{
                    toolbarImage.load(picvalue)
                    {
                        error(R.drawable.ic_cuisine)
                        placeholder(R.drawable.ic_cuisine)
                        crossfade(false)
                    }
                }?:let{
                    toolbarImage.load(R.drawable.ic_cuisine)
                }

            }
        }
    }
}