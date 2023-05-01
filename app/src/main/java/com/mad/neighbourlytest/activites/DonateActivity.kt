package com.mad.neighbourlytest.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.databinding.ActivityDonateBinding

class DonateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDonateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.DonateContBtn.setOnClickListener{
            startActivity(Intent(this, Donate2AddInfoActivity::class.java))
        }
    }
}