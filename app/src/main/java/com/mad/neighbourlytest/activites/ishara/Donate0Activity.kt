package com.mad.neighbourlytest.activites.ishara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.activites.isuru.Donate2AddInfoActivity
import com.mad.neighbourlytest.activites.isuru.DonateActivity
import com.mad.neighbourlytest.databinding.ActivityDonate0Binding

class Donate0Activity : AppCompatActivity() {

    private lateinit var binding: ActivityDonate0Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonate0Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.donateMainBtn.setOnClickListener {
            val intent = Intent(this, DonateActivity::class.java)
            intent.putExtra("type","main")
            startActivity(intent)
        }

        binding.donateCategoryBtn.setOnClickListener {
            startActivity(Intent(this, DonationCategory::class.java))
        }

    }
}