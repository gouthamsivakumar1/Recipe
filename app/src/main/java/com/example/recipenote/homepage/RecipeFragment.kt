package com.example.recipenote.homepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.recipenote.R
import com.example.recipenote.Utils.setRxOnClickListener
import com.example.recipenote.homepage.adapter.RecipeAdapter
import com.example.recipenote.viewmodel.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_recipe_framgent.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RecipeFragment : Fragment() {
    private  val mAdapter  = RecipeAdapter()
    private  val mViewModel:RecipeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_framgent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        loadButtonFunctionality()

    }

    private fun loadButtonFunctionality() {
       svSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
           android.widget.SearchView.OnQueryTextListener {
           override fun onQueryTextSubmit(query: String?): Boolean {
               return false
           }

           override fun onQueryTextChange(query: String?): Boolean {
               if(query.isNullOrEmpty()) {
                   loadData()
               }
               else{
                   loadRecipeData(query)
               }
               return true
           }
       })
               btnAdd.setRxOnClickListener {
            findNavController().navigate(R.id.action_recipeFramgent_to_writeYourRecipe)
        }

    }

    private fun setAdapter() {
        rvRecipeItems.adapter =mAdapter
       /* mViewModel.getRecipeList().observe(this, Observer {
            mAdapter.setData(it)
            mAdapter.notifyDataSetChanged()
        })*/

    }
    private  fun loadData() {
        mViewModel.getRecipeList().observe(this, Observer {
            mAdapter.setData(it)
            mAdapter.notifyDataSetChanged()
            mAdapter.setProfileCallback { data ->
                Log.i("tag", " data for recipe model $data")
                mViewModel.setDetails(data)
                findNavController().navigate(R.id.action_recipeFramgent_to_recipeDetailsPage)
            }
        })
    }
        private  fun loadRecipeData( recipe:String)
        {
            mViewModel.getRecipe(recipe).observe(this, Observer {
                mAdapter.setData(it)
                mAdapter.notifyDataSetChanged()
                mAdapter.setProfileCallback { data ->
                    Log.i("tag", " data for recipe model $data")
                    mViewModel.setDetails(data)
                    findNavController().navigate(R.id.action_recipeFramgent_to_recipeDetailsPage)
                }
            })
    }

    override fun onResume() {
        loadData()
        super.onResume()

    }
}