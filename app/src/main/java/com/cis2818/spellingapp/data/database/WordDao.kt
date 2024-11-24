package com.cis2818.spellingapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg words: Word)

    // Note that this uses the primary key
    @Update
    suspend fun updateWords(vararg words: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("DELETE FROM word WHERE name = :wordName")
    suspend fun deleteWordByName(wordName: String)

    @Query("SELECT * FROM word")
    suspend fun getAll(): List<Word>

    @Query("SELECT * FROM word WHERE name = :wordName")
    suspend fun getWordByName(wordName: String): Word?
}