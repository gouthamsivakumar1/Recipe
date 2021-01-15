package com.example.recipenote.splashScreenAcitivty

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.recipenote.LoginPage.MainActivity
import com.example.recipenote.R
import com.example.recipenote.databinding.ActivitySplashScreenBinding
import com.example.recipenote.homepage.RecipeActivity
import com.example.recipenote.viewmodel.LoginViewModel
import com.example.recipenote.viewobserver.SplashScreenViewObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashScreenActivity : AppCompatActivity() {
    var SPLASH_SCREEN_TIME_OUT =1000L;
    private var mViewObserver = SplashScreenViewObserver()
    private val mViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        val binding = DataBindingUtil.setContentView<ActivitySplashScreenBinding>(
            this,
            R.layout.activity_splash_screen
        )
        binding.data = mViewObserver
        loadSplashScreen()
    }

    /**
     *  load splash screen event
     */
    private fun loadSplashScreen() {
             var userName =""
        mViewObserver.setData(getString(R.string.app_name))
        mViewModel.getList()?.observe(this, Observer { login ->
            login?.let{
                userName = login.username
            }
        })
        GlobalScope.launch(Dispatchers.IO) {
            delay(SPLASH_SCREEN_TIME_OUT)

                if (userName.isNullOrEmpty()) {
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                    finish()

                } else {
                    startActivity(Intent(this@SplashScreenActivity, RecipeActivity::class.java))
                    finish()
                }

            //the current activity will get finished.
        }
    }

}