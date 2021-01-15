package com.example.recipenote.LoginPage
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.recipenote.R
import com.example.recipenote.Utils.setRxOnClickListener
import com.example.recipenote.homepage.RecipeActivity
import com.example.recipenote.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private  val  mLoginViewModel:LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setButtonFunctionality()
    }

    private fun setButtonFunctionality() {
        btnSignUp.setRxOnClickListener {
            if( etUserName.text!!.isNotEmpty())
            {
                GlobalScope.launch(Dispatchers.IO) {
                    mLoginViewModel.setUserName(
                        etUserName.text.toString(),
                        etPassword.text.toString()
                    )
                    delay(3000L)

                }
                startActivity(Intent(this,RecipeActivity::class.java))

            }
        }

        tvNoAccount.setOnClickListener {
            mLoginViewModel.getList().observe(this, Observer {
                run {
                    Toast.makeText(this, "${it}", Toast.LENGTH_LONG).show()
                }
        })

    }
}
}