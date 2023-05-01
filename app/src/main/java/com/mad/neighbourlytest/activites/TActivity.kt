package com.mad.neighbourlytest.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.databinding.ActivityTactivityBinding

class TActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}