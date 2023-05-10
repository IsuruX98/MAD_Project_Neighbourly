package com.mad.neighbourlytest.activites.dinidu


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mad.neighbourlytest.activites.isuru.HomeActivity
import com.mad.neighbourlytest.activites.isuru.Menu
import com.mad.neighbourlytest.activites.isuru.Menu2
import com.mad.neighbourlytest.databinding.ActivityFamilyListBinding

class FamilyList : AppCompatActivity() {


    private lateinit var binding: ActivityFamilyListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFamilyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editBtn.setOnClickListener {
            startActivity(Intent(this, EditFamilyActivity::class.java))
        }
        binding.deleteBtn.setOnClickListener {
            Toast.makeText(this, "delete works", Toast.LENGTH_SHORT).show()
        }

        binding.menuHome.setOnClickListener{

            //making a sharedPreference to access in the app
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val type2 = sharedPreferences.getString("type", "").toString()
            if(type2=="Donor"){
                startActivity(Intent(this, Menu2::class.java))
            }else{
                startActivity(Intent(this, Menu::class.java))
            }

        }
        binding.menuHome2.setOnClickListener{

            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}