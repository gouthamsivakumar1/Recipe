package com.example.recipenote.homepage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.example.recipenote.Utils.setRxOnClickListener
import com.example.recipenote.databinding.FragmentWriteYourRecipeBinding
import com.example.recipenote.model.RecipeModel
import com.example.recipenote.viewmodel.RecipeViewModel
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.ByteArrayOutputStream

class WriteYourRecipe : Fragment() {

    private lateinit var mViewBinding: FragmentWriteYourRecipeBinding
    private  val mViewModel:RecipeViewModel by sharedViewModel()
    private  var mPicture:ByteArray = byteArrayOf()
    private  var job:Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = FragmentWriteYourRecipeBinding.inflate(inflater, container, false)
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadButtonFunctionality()
    }



    private fun loadButtonFunctionality() {
        mViewBinding.imRecipe.setRxOnClickListener {
            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,0)
        }

        mViewBinding.btnSubmit.setRxOnClickListener {
            if (!mViewBinding.etHowToMake.text.isNullOrEmpty() || !mViewBinding.etIngredients.text.isNullOrEmpty() || !mViewBinding.etRecipe.text.isNullOrEmpty()) {
                mPicture?.let{
               job = GlobalScope.launch(Dispatchers.IO) {
                    mPicture?.let {

                        mViewModel.setItemList(
                            RecipeModel(
                                0,
                                mViewBinding.etRecipe.text.toString(),
                                mViewBinding.etIngredients.text.toString(),
                                mViewBinding.etHowToMake.text.toString(),
                                it
                            )

                        )
                    }
                    delay(1000L)
                    findNavController().navigateUp()
                }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let{
            val Bitmap =data?.extras?.get("data") as Bitmap
            val stream = ByteArrayOutputStream()
            Bitmap.compress(CompressFormat.PNG, 100, stream)
            mPicture = stream.toByteArray()
            mViewBinding.imRecipe.load(Bitmap){
                crossfade(false)
                transformations(RoundedCornersTransformation(30f))
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job?.cancel()
    }
}