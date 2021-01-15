package com.example.recipenote.repository.db.api

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipenote.model.Login


@Dao
interface LoginDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(login: Login)

    @Query("DELETE FROM Login")
     fun deleteAll()

    @Query("SELECT * from Login ORDER BY FirstName ASC")
     fun getAlphabetizedWords(): LiveData<Login>

}