package com.cis2818.spellingapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg words: Word)

    // Note that this uses the primary key
    @Update
    fun updateWords(vararg words: Word)

    // The delete methods return the number of rows deleted
    @Delete
    fun delete(word: Word): Int

    @Query("DELETE FROM word WHERE name = :wordName")
    fun deleteWordByName(wordName: String): Int

    @Query("SELECT * FROM word")
    fun getAll(): List<Word>

    @Query("SELECT * FROM word WHERE name = :wordName")
    fun getWordByName(wordName: String): Word?
}