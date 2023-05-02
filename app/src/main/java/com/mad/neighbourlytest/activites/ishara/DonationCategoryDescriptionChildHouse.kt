package com.mad.neighbourlytest.activites.ishara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.activites.isuru.DonateActivity
import com.mad.neighbourlytest.databinding.ActivityCategoryDonateBinding

class DonationCategoryDescriptionChildHouse : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryDonateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDonateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.orphanBtnDonateNow.setOnClickListener {
            startActivity(Intent(this, DonateActivity::class.java))
        }
    }
}