package com.cis2818.spellingapp

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cis2818.spellingapp.data.database.AppDatabase
import com.cis2818.spellingapp.data.database.Word
import com.cis2818.spellingapp.data.database.WordDao
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.core.app.ApplicationProvider
import androidx.lifecycle.lifecycleScope
import androidx.test.core.app.ActivityScenario
import com.cis2818.spellingapp.ui.activities.MainActivity
import kotlinx.coroutines.launch

@RunWith(AndroidJUnit4::class)
class DatabaseTest  {

    private lateinit var database: AppDatabase
    private  lateinit var wordDao: WordDao
    private lateinit var testWord: Word

    @Before
    fun setupDb() {

        Log.v("test", "test 1 ")

        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()

        Log.v("test", "test 2")

        wordDao = database.wordDao()
        Log.v("test", "test 3")

        testWord = Word("The", "", "")
        Log.v("test", "test 4")
    }

    @After
    fun closeDb() {
        Log.v("test", "test END")
        database.close()
    }

    @Test
    fun addition_isCorrect() {
        Log.v("test", "test 5")
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testInsertAll(){
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity { activity ->
            activity.lifecycleScope.launch {
                wordDao.insertAll(testWord)
            }
        }
    }
    @Test
    fun testDelete(){

        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity { activity ->
            activity.lifecycleScope.launch {
                assertEquals(1 , wordDao.delete(testWord))
            }
        }

    }
}