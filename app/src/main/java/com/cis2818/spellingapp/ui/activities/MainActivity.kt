package com.cis2818.spellingapp.ui.activities
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cis2818.spellingapp.R
import com.cis2818.spellingapp.data.database.AppDatabase
import com.cis2818.spellingapp.data.database.Word
import com.cis2818.spellingapp.databinding.ActivityMainBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = AppDatabase.getDatabase(this)
        val wordDao = db.wordDao()
        val testWord = Word("Hello", "History example", "2024-11-24")
        lifecycleScope.launch{
            wordDao.insertAll(testWord)
            val wordsReturned = wordDao.getAll()
            wordsReturned.forEach { word ->
                println("Word: ${word.name}")
            }
        }
    }
}