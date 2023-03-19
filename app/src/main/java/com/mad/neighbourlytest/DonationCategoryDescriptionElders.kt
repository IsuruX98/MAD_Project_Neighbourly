package com.mad.neighbourlytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.databinding.ActivityDonationCategoryDescriptionEldersBinding

class DonationCategoryDescriptionElders : AppCompatActivity() {

    private lateinit var binding: ActivityDonationCategoryDescriptionEldersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationCategoryDescriptionEldersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.orphanBtnDonateNow.setOnClickListener {
            startActivity(Intent(this,DonateActivity::class.java))
        }
    }
}