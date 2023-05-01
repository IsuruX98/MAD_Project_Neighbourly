package com.mad.neighbourlytest.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.databinding.ActivityDonationCategoryBinding

class DonationCategory : AppCompatActivity() {

    private lateinit var binding: ActivityDonationCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.childOrphanageBtn.setOnClickListener {
            startActivity(Intent(this, DonationCategoryDescriptionChildHouse::class.java))
        }
        binding.eldersHomeBtn.setOnClickListener {
            startActivity(Intent(this, DonationCategoryDescriptionElders::class.java))
        }
        binding.lowIncomeFamBtn.setOnClickListener {
            startActivity(Intent(this, DonationCategoryDescriptionLowIncomeFam::class.java))
        }
        binding.lowFacilitySchoolsBtn.setOnClickListener {
            startActivity(Intent(this, DonationCategoryDescriptionLowFacilitySchools::class.java))
        }
    }
}