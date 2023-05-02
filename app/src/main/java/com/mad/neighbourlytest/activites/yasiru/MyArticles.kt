package com.mad.neighbourlytest.activites.yasiru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.databinding.ActivityMyArticlesBinding

class MyArticles : AppCompatActivity() {

    private lateinit var binding: ActivityMyArticlesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editBtn.setOnClickListener {
            startActivity(Intent(this, EditArticleActivity::class.java))
        }
        binding.viewBtn.setOnClickListener {
            startActivity(Intent(this, Article::class.java))
        }
    }
}