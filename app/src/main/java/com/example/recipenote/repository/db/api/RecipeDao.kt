package com.example.recipenote.repository.db.api
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipenote.model.RecipeModel
import retrofit2.http.DELETE


@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(login: RecipeModel)

    @Query("DELETE FROM RecipeModel")
    fun deleteAll()

    @Query("SELECT * from RecipeModel ORDER BY Recipe ASC")
    fun getAlphabetizedWords(): LiveData<List<RecipeModel>>

    @Query("DELETE  FROM RecipeModel Where Recipe = :recipe" )
    fun deleteRecipe(recipe:String)

    @Query("SELECT * from RecipeModel Where Recipe Like:recipe||'%' ORDER BY Recipe ASC")
    fun getRecipe(recipe: String) :LiveData<List<RecipeModel>>

}