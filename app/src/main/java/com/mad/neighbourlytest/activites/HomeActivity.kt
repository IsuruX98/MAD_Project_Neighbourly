package com.mad.neighbourlytest.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fundOverviewBtn.setOnClickListener {
            startActivity(Intent(this, FundOverview::class.java))
        }

        binding.homeDonateNowBtn.setOnClickListener {
            startActivity(Intent(this, Donate0Activity::class.java))
        }
        binding.seeAllBtn.setOnClickListener{
            startActivity(Intent(this, Menu2::class.java))
        }
        binding.menuProfile.setOnClickListener{
            startActivity(Intent(this, Profile::class.java))
        }
        binding.conatctusBtnHome.setOnClickListener{
            startActivity(Intent(this, ContactUsActivity::class.java))
        }
        binding.categoryBtn.setOnClickListener{
            startActivity(Intent(this, Menu2::class.java))
        }
        binding.AboutUsBtnHome.setOnClickListener{
            startActivity(Intent(this, AboutUsActivity::class.java))
        }

    }
}