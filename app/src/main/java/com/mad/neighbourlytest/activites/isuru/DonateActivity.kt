package com.mad.neighbourlytest.activites.isuru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mad.neighbourlytest.databinding.ActivityDonateBinding

class DonateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDonateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.DonateAmount.isEnabled = false

        binding.Rs10.setOnClickListener {
            binding.DonateAmount.setText("10")
        }
        binding.Rs25.setOnClickListener {
            binding.DonateAmount.setText("25")
        }
        binding.Rs50.setOnClickListener {
            binding.DonateAmount.setText("50")
        }
        binding.Rs100.setOnClickListener {
            binding.DonateAmount.setText("100")
        }
        binding.Rs250.setOnClickListener {
            binding.DonateAmount.setText("250")
        }
        binding.CustomAmount.setOnClickListener {
            binding.DonateAmount.setText("")
            binding.DonateAmount.isEnabled = true
        }

        binding.DonateContBtn.setOnClickListener{
            val amount = binding.DonateAmount.text.toString()
            if(amount.isEmpty()){
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, Donate2AddInfoActivity::class.java)
            intent.putExtra("amount",amount)
            startActivity(intent)
        }
    }
}