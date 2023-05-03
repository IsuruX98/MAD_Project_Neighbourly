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
import com.mad.neighbourlytest.models.MainFundModel
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

        binding.DinfoPaymentAmount.text = amount
        binding.DinfoPaymentTotal.text = amount
        binding.DinfoName.setText(name2)
        binding.DinfoEmail.setText(email2)

        binding.DinfoBtn.setOnClickListener {
            insert()
        }
    }

    private fun insert() {
        val name = binding.DinfoName.text.toString()
        val email = binding.DinfoEmail.text.toString()
        val comment = binding.DinfoComment.text.toString()
        val amount = binding.DinfoPaymentTotal.text.toString()

        val fund = MainFundModel(name, email, comment, amount)

        val id = UUID.randomUUID().toString()

        database.getReference("mainFund").child(id).setValue(fund)
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