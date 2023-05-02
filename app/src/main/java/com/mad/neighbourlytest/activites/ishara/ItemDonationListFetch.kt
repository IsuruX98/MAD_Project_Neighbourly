package com.mad.neighbourlytest.activites.ishara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mad.neighbourlytest.databinding.ActivityItemDonationListOneBinding

class ItemDonationListFetch : AppCompatActivity() {

    private lateinit var binding: ActivityItemDonationListOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDonationListOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteBtn.setOnClickListener {
            Toast.makeText(this, "delete works", Toast.LENGTH_SHORT).show()
        }
        binding.dispatchedBtn.setOnClickListener {
            Toast.makeText(this, "items dispatched", Toast.LENGTH_SHORT).show()
        }
    }
}