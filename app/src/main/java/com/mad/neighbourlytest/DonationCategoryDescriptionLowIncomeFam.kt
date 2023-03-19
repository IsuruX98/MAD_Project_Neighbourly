package com.mad.neighbourlytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.databinding.ActivityDonationCategoryDescriptionLowIncomeFamBinding

class DonationCategoryDescriptionLowIncomeFam : AppCompatActivity() {

    private lateinit var binding: ActivityDonationCategoryDescriptionLowIncomeFamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationCategoryDescriptionLowIncomeFamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.orphanBtnDonateNow.setOnClickListener {
            startActivity(Intent(this,DonateActivity::class.java))
        }
    }
}