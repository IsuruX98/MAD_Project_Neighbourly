package com.mad.neighbourlytest.activites.ishara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.activites.isuru.DonateActivity
import com.mad.neighbourlytest.databinding.ActivityDonationCategoryDescriptionLowFacilitySchoolsBinding

class DonationCategoryDescriptionLowFacilitySchools : AppCompatActivity() {

    private lateinit var binding: ActivityDonationCategoryDescriptionLowFacilitySchoolsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationCategoryDescriptionLowFacilitySchoolsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.orphanBtnDonateNow.setOnClickListener {
            val intent = Intent(this, DonateActivity::class.java)
            intent.putExtra("type","lowFacilitySchool")
            startActivity(intent)
        }
    }
}