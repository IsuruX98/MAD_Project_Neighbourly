package com.mad.neighbourlytest.activites.isuru

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.databinding.ActivityFundOverviewBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FundOverview : AppCompatActivity() {

    private lateinit var binding: ActivityFundOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFundOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //making a sharedPreference to access in the app
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val email2 = sharedPreferences.getString("email", "").toString()
        val type2 = sharedPreferences.getString("type", "").toString()

        val database = FirebaseDatabase.getInstance()
        val mainFundRef = database.getReference("funds")

        //calculate the total donations that made by all the users

        mainFundRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // total amount sum in the `totalAmount` variable
                // update the TextView
                binding.totalFund.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading : ${error.message}")
            }
        })

        if(type2 == "Admin"){

            //calculate the user count

            val db = FirebaseFirestore.getInstance()
            val usersRef = db.collection("USERS")

            usersRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val count = task.result?.size() ?: 0
                    binding.adminText.text = "Total Users"
                    binding.yourDonations.text = "$count"
                } else {
                    Log.e("Firestore", "Error getting documents: ${task.exception}")
                }
            }
        }else{
            //calculate the total donations that made by the logged in user

            val query = mainFundRef.orderByChild("email").equalTo(email2)

            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var totalAmount = 0
                    for (childSnapshot in dataSnapshot.children) {
                        val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                        totalAmount += amount
                    }
                    // total amount sum for the specified username in the `totalAmount` variable
                    // update the TextView
                    binding.yourDonations.text = "Rs. $totalAmount"
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Firebase", "Error reading : ${error.message}")
                }
            })
        }

        //calculate the total donations that made by today date

        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) // Get today's date in the format "yyyy-MM-dd"

        val query2 = mainFundRef.orderByChild("date").equalTo(today)

        query2.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // total amount sum for today's date in the `totalAmount` variable
                // update the TextView
                binding.todayDonations.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading : ${error.message}")
            }
        })

        //calculate the total donations that made to the child house fund

        val query3 = mainFundRef.orderByChild("type").equalTo("childHouse")

        query3.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // total amount sum for the specified type in the `totalAmount` variable
                // update the TextView
                binding.childFund.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading : ${error.message}")
            }
        })

        //calculate the total donations that made to the elders fund

        val query4 = mainFundRef.orderByChild("type").equalTo("elders")

        query4.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // total amount sum for the specified type in the `totalAmount` variable
                // update the TextView
                binding.eldersFund.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading : ${error.message}")
            }
        })

        //calculate the total donations that made to the low income family fund

        val query5 = mainFundRef.orderByChild("type").equalTo("lowIncomeFamily")

        query5.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // total amount sum for the specified type in the `totalAmount` variable
                // update the TextView
                binding.familyFund.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading : ${error.message}")
            }
        })

        //calculate the total donations that made to the low facility school fund

        val query6 = mainFundRef.orderByChild("type").equalTo("lowFacilitySchool")

        query6.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // total amount sum for the specified type in the `totalAmount` variable
                // update the TextView
                binding.schoolFund.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading : ${error.message}")
            }
        })

        //calculate the total donations that made to the main fund

        val query7 = mainFundRef.orderByChild("type").equalTo("main")

        query7.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // total amount sum for the specified type in the `totalAmount` variable
                // update the TextView
                binding.mainFund.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading : ${error.message}")
            }
        })

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