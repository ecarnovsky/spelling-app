package com.cis2818.spellingapp.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * @Deprecated
 */
class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)  {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = "CREATE TABLE $TABLE_NAME ($COLUMN_WORD TEXT PRIMARY KEY, $COLUMN_HISTORY TEXT, $COLUMN_LAST_REVIEWED TEXT)"
        db?.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "spelling_app.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "words"
        private const val COLUMN_WORD = "word"
        private const val COLUMN_HISTORY = "history"
        private const val COLUMN_LAST_REVIEWED = "last_reviewed"
    }
}