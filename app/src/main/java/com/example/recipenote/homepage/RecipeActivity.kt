package com.example.recipenote.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.recipenote.R
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        loadNavigationFunctionality()

    }




    private fun loadNavigationFunctionality() {
             val mNavigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
            bottom_nav_view.setupWithNavController(mNavigationController)
    }

}