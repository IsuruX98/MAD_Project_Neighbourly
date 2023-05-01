package com.mad.neighbourlytest.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mad.neighbourlytest.databinding.ActivityFamilyListBinding

class FamilyList : AppCompatActivity() {

    private lateinit var binding: ActivityFamilyListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFamilyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editBtn.setOnClickListener {
            startActivity(Intent(this, EditFamilyActivity::class.java))
        }
        binding.deleteBtn.setOnClickListener {
            Toast.makeText(this, "delete works", Toast.LENGTH_SHORT).show()
        }
    }
}