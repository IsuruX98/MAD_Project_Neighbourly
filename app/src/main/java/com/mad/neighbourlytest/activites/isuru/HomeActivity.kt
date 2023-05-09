package com.mad.neighbourlytest.activites.isuru

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.mad.neighbourlytest.activites.dinidu.AboutUsActivity
import com.mad.neighbourlytest.activites.dinidu.ContactUsActivity
import com.mad.neighbourlytest.activites.ishara.Donate0Activity
import com.mad.neighbourlytest.databinding.ActivityHomeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        //checking the user is logged in or not
        if (currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {

            //creating the shared preference to access the data of the user in the app
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            var email = currentUser.email

            if (email != null) {
                db = FirebaseFirestore.getInstance()

                db.collection("USERS").document(email).get().addOnSuccessListener { task ->
                    var name = task.get("name").toString()
                    var email = task.get("email").toString()
                    var id = task.get("id").toString()
                    var mobile = task.get("mobile").toString()
                    var type = task.get("type").toString()

                    //put data into the shared preference
                    editor.putString("name", name)
                    editor.putString("email", email)
                    editor.putString("mobile", mobile)
                    editor.putString("id", id)
                    editor.putString("type", type)
                    editor.apply()

                    val name2 = sharedPreferences.getString("name", "").toString()

                    binding.menuUserName.text = name2

                }
            }


            binding.fundOverviewBtn.setOnClickListener {
                startActivity(Intent(this, FundOverview::class.java))
            }

            binding.homeDonateNowBtn.setOnClickListener {
                startActivity(Intent(this, Donate0Activity::class.java))
            }
            binding.seeAllBtn.setOnClickListener {
                val type2 = sharedPreferences.getString("type", "").toString()
                if (type2 == "Donor") {
                    startActivity(Intent(this, Menu2::class.java))
                }
                else if (type2 == "Admin") {
                    startActivity(Intent(this, MenuAdmin::class.java))
                } else {
                    startActivity(Intent(this, Menu::class.java))
                }

            }
            binding.menuProfile.setOnClickListener {
                startActivity(Intent(this, Profile::class.java))
            }
            binding.conatctusBtnHome.setOnClickListener {
                startActivity(Intent(this, ContactUsActivity::class.java))
            }
            binding.categoryBtn.setOnClickListener {
                val type2 = sharedPreferences.getString("type", "").toString()
                if (type2 == "Donor") {
                    startActivity(Intent(this, Menu2::class.java))
                }
                else if (type2 == "Admin") {
                    startActivity(Intent(this, MenuAdmin::class.java))
                } else {
                    startActivity(Intent(this, Menu::class.java))
                }

            }
            binding.AboutUsBtnHome.setOnClickListener {
                startActivity(Intent(this, AboutUsActivity::class.java))
            }

            //calculate the total donations that made by all the users

            val database = FirebaseDatabase.getInstance()
            val mainFundRef = database.getReference("funds")

            mainFundRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var totalAmount = 0
                    for (childSnapshot in dataSnapshot.children) {
                        val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                        totalAmount += amount
                    }
                    // total amount sum in the `totalAmount` variable
                    // update the TextView
                    binding.totalDonations.text = "Rs. $totalAmount"
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Firebase", "Error reading : ${error.message}")
                }
            })

            val userEmail = currentUser?.email
            if(userEmail == "admin@gmail.com"){

                //calculate the user count

                val db = FirebaseFirestore.getInstance()
                val usersRef = db.collection("USERS")

                usersRef.addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        Log.e("Firestore", "Error listening to documents: $e")
                        return@addSnapshotListener
                    }

                    if (snapshot != null) {
                        val count = snapshot.size()
                        binding.textView31.text = "Total Users"
                        binding.your.text = "$count"
                    }
                }
            }else{
                //calculate the total donations that made by the logged in user
                val userEmail = currentUser?.email
                val query = mainFundRef.orderByChild("email").equalTo(userEmail)

                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        var totalAmount = 0
                        for (childSnapshot in dataSnapshot.children) {
                            val amount = childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                            totalAmount += amount
                        }
                        // total amount sum for the specified username in the `totalAmount` variable
                        // update the TextView
                        binding.your.text = "Rs. $totalAmount"
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("Firebase", "Error reading : ${error.message}")
                    }
                })
            }

            //calculate the total donations that made by all users today

            val today = SimpleDateFormat(
                "yyyy-MM-dd",
                Locale.getDefault()
            ).format(Date()) // Get today's date in the format "yyyy-MM-dd"

            val query2 = mainFundRef.orderByChild("date").equalTo(today)

            query2.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var totalAmount = 0
                    for (childSnapshot in dataSnapshot.children) {
                        val amount =
                            childSnapshot.child("amount").getValue(String::class.java)?.toInt() ?: 0
                        totalAmount += amount
                    }
                    // total amount sum for today's date in the `totalAmount` variable
                    // update the TextView
                    binding.today.text = "Rs. $totalAmount"
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Firebase", "Error reading : ${error.message}")
                }
            })


        }

    }
}