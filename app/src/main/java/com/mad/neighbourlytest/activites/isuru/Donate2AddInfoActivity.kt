package com.mad.neighbourlytest.activites.isuru

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mad.neighbourlytest.activites.ishara.ThankYouActivity
import com.mad.neighbourlytest.databinding.ActivityDonate2AddInfoBinding
import com.mad.neighbourlytest.models.isuru.FundModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class Donate2AddInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDonate2AddInfoBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonate2AddInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        //making a sharedPreference to access in the app
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val name2 = sharedPreferences.getString("name", "").toString()
        val email2 = sharedPreferences.getString("email", "").toString()

        val amount = intent.getStringExtra("amount").toString()


        binding.DinfoPaymentAmount.text = "Rs. $amount"
        binding.DinfoPaymentTotal.text = "Rs. $amount"
        binding.DinfoName.setText(name2)
        binding.DinfoEmail.setText(email2)

        binding.DinfoBtn.setOnClickListener {
            insert()
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

    private fun insert() {



        val name = binding.DinfoName.text.toString()
        val email = binding.DinfoEmail.text.toString()
        val comment = binding.DinfoComment.text.toString()
        val amount = intent.getStringExtra("amount").toString()
        val today = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        ).format(Date()) // Get today's date in the format "yyyy-MM-dd"

        val type = intent.getStringExtra("type").toString()

        val fund = FundModel(name, email, comment, amount, today, type)


        val id = UUID.randomUUID().toString()

        database.getReference("funds").child(id).setValue(fund)
            .addOnSuccessListener {
                Toast.makeText(this, "Donation successfully added", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ThankYouActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Donation Failed", Toast.LENGTH_SHORT).show()
            }
    }

}