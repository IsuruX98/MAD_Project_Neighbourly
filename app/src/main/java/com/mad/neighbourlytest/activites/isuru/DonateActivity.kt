package com.mad.neighbourlytest.activites.isuru

import android.content.Context
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

        val type = intent.getStringExtra("type").toString()

        binding.DonateContBtn.setOnClickListener{
            val amount = binding.DonateAmount.text.toString()
            if(amount.isEmpty()){
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, Donate2AddInfoActivity::class.java)
            intent.putExtra("amount",amount)
            intent.putExtra("type",type)
            startActivity(intent)
        }
        binding.menuHome.setOnClickListener {
            //making a sharedPreference to access in the app
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val type2 = sharedPreferences.getString("type", "").toString()
            if(type2=="Donor"){
                startActivity(Intent(this, Menu2::class.java))
            }else{
                startActivity(Intent(this, Menu::class.java))
            }
        }
        binding.menuHome2.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}