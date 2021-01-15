package com.example.recipenote.injection
import androidx.room.Room
import com.example.recipenote.Repository.LoginRepository
import com.example.recipenote.Repository.RecipeRepository
import com.example.recipenote.repository.db.api.AppDatabase
import com.example.recipenote.viewmodel.LoginViewModel
import com.example.recipenote.viewmodel.RecipeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun appModule() = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, "appDataBase"
        ).build()
    }

    single { LoginRepository(get())}

    single { RecipeRepository(get()) }

    viewModel { LoginViewModel(get()) }

    viewModel { RecipeViewModel(get()) }
}
