package com.mad.neighbourlytest.activites.isuru

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

        val name2 = sharedPreferences.getString("name", "").toString()
        val email2 = sharedPreferences.getString("email", "").toString()

        val database = FirebaseDatabase.getInstance()
        val mainFundRef = database.getReference("funds")

        mainFundRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // Now you have the total amount sum in the `totalAmount` variable
                // Do whatever you want with it, e.g. update a TextView
                binding.totalFund.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading mainFund: ${error.message}")
            }
        })

        val query = mainFundRef.orderByChild("email").equalTo(email2)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // Now you have the total amount sum for the specified username in the `totalAmount` variable
                // Do whatever you want with it, e.g. update a TextView
                binding.yourDonations.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading mainFund: ${error.message}")
            }
        })

        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) // Get today's date in the format "yyyy-MM-dd"

        val query2 = mainFundRef.orderByChild("date").equalTo(today)

        query2.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // Now you have the total amount sum for today's date in the `totalAmount` variable
                // Do whatever you want with it, e.g. update a TextView
                binding.todayDonations.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading mainFund: ${error.message}")
            }
        })

        val query3 = mainFundRef.orderByChild("type").equalTo("childHouse")

        query3.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // Now you have the total amount sum for the specified username in the `totalAmount` variable
                // Do whatever you want with it, e.g. update a TextView
                binding.childFund.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading mainFund: ${error.message}")
            }
        })

        val query4 = mainFundRef.orderByChild("type").equalTo("elders")

        query4.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // Now you have the total amount sum for the specified username in the `totalAmount` variable
                // Do whatever you want with it, e.g. update a TextView
                binding.eldersFund.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading mainFund: ${error.message}")
            }
        })

        val query5 = mainFundRef.orderByChild("type").equalTo("lowIncomeFamily")

        query5.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // Now you have the total amount sum for the specified username in the `totalAmount` variable
                // Do whatever you want with it, e.g. update a TextView
                binding.familyFund.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading mainFund: ${error.message}")
            }
        })

        val query6 = mainFundRef.orderByChild("type").equalTo("lowFacilitySchool")

        query6.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // Now you have the total amount sum for the specified username in the `totalAmount` variable
                // Do whatever you want with it, e.g. update a TextView
                binding.schoolFund.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading mainFund: ${error.message}")
            }
        })

        val query7 = mainFundRef.orderByChild("type").equalTo("main")

        query7.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0
                for (childSnapshot in dataSnapshot.children) {
                    val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                    totalAmount += amount
                }
                // Now you have the total amount sum for the specified username in the `totalAmount` variable
                // Do whatever you want with it, e.g. update a TextView
                binding.mainFund.text = "Rs. $totalAmount"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading mainFund: ${error.message}")
            }
        })


    }
}