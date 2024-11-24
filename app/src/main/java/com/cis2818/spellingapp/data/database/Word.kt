package com.cis2818.spellingapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word(
    @PrimaryKey
    val name: String,
    val history: String?,
    val lastReviewed: String?
)