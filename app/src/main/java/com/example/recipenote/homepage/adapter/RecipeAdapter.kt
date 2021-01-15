package com.example.recipenote.homepage.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.recipenote.BaseRecyclerAdapter
import com.example.recipenote.databinding.RecipeListItemBinding
import com.example.recipenote.homepage.viewholder.RecipeViewHolder
import com.example.recipenote.model.RecipeModel
import com.example.recipenote.viewobserver.RecipeViewObserver

class RecipeAdapter(): BaseRecyclerAdapter<RecipeModel, RecipeViewHolder>(){
    private lateinit var mDetailsCallback: (RecipeModel) -> Unit?


    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup,
        viewType: Int
    ): RecipeViewHolder {
        val binding = RecipeListItemBinding.inflate(inflater, container, false)
        binding.data = RecipeViewObserver()
        return RecipeViewHolder(binding,mDetailsCallback)
    }
    fun setProfileCallback(callback: (RecipeModel) -> Unit?) {
        mDetailsCallback = callback
    }

}