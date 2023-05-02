package com.mad.neighbourlytest.activites.isuru

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mad.neighbourlytest.activites.dinidu.AboutUsActivity
import com.mad.neighbourlytest.activites.dinidu.ContactUsActivity
import com.mad.neighbourlytest.activites.ishara.Donate0Activity
import com.mad.neighbourlytest.databinding.ActivityHomeBinding

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

        if (currentUser == null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else {

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

                    editor.putString("name", name)
                    editor.putString("email", email)
                    editor.putString("mobile", mobile)
                    editor.putString("id", id)
                    editor.putString("type", type)
                    editor.apply()

                    val name2 = sharedPreferences.getString("name", "").toString()
                    val email2 = sharedPreferences.getString("email", "").toString()
                    val mobile2 = sharedPreferences.getString("mobile", "").toString()
                    val id2 = sharedPreferences.getString("id", "").toString()
                    val type2 = sharedPreferences.getString("type", "").toString()

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
                startActivity(Intent(this, Menu2::class.java))
            }
            binding.menuProfile.setOnClickListener {
                startActivity(Intent(this, Profile::class.java))
            }
            binding.conatctusBtnHome.setOnClickListener {
                startActivity(Intent(this, ContactUsActivity::class.java))
            }
            binding.categoryBtn.setOnClickListener {
                startActivity(Intent(this, Menu2::class.java))
            }
            binding.AboutUsBtnHome.setOnClickListener {
                startActivity(Intent(this, AboutUsActivity::class.java))
            }

        }

    }
}