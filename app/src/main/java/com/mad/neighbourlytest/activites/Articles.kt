package com.mad.neighbourlytest.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.databinding.ActivityBlogsBinding

class Articles : AppCompatActivity() {

    private lateinit var binding: ActivityBlogsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.at1Btn.setOnClickListener {
            startActivity(Intent(this, Article::class.java))
        }
    }
}