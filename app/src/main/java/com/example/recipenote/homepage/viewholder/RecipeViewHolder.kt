package com.example.recipenote.homepage.viewholder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import coil.api.load
import com.example.recipenote.BaseViewHolderItem
import com.example.recipenote.R
import com.example.recipenote.Utils.setRxOnClickListener
import com.example.recipenote.databinding.RecipeListItemBinding
import com.example.recipenote.model.RecipeModel
import kotlinx.android.synthetic.main.activity_splash_screen.view.*
import kotlinx.android.synthetic.main.recipe_list_item.*
import kotlinx.android.synthetic.main.recipe_list_item.view.*

class RecipeViewHolder(private val mBinding:RecipeListItemBinding,private  val mDetails:(RecipeModel)->Unit?):BaseViewHolderItem<RecipeModel>(mBinding.root) {
    override fun onCreated() {
        itemView.cdRecipe.setRxOnClickListener {
            data?.let{
                mDetails(it)
            }
        }

    }

    override fun onBind(data: RecipeModel) {
        mBinding.data?.setData(data)
        data?.picValue?.let{
            val picvalue =BitmapFactory.decodeByteArray(it, 0, it.size)
            picvalue?.let {
                itemView.imImage.load(picvalue){
                    crossfade(true)
                }
            }?:  itemView.imImage.load(R.drawable.ic_cuisine)


        }

        }
    }

