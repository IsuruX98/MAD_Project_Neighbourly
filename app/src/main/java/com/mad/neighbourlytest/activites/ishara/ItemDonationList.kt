package com.mad.neighbourlytest.activites.ishara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mad.neighbourlytest.databinding.ActivityDonationListBinding

class ItemDonationList : AppCompatActivity() {

    private lateinit var binding: ActivityDonationListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteBtn.setOnClickListener {
            Toast.makeText(this, "delete works", Toast.LENGTH_SHORT).show()
        }
        binding.dispatchedBtn.setOnClickListener {
            Toast.makeText(this, "items dispatched", Toast.LENGTH_SHORT).show()
        }
    }
}