package com.mad.neighbourlytest.activites.yasiru

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.activites.isuru.HomeActivity
import com.mad.neighbourlytest.activites.isuru.Menu
import com.mad.neighbourlytest.activites.isuru.Menu2

class AddArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_article)
//        binding.menuHome.setOnClickListener {
//            //making a sharedPreference to access in the app
//            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//            val type2 = sharedPreferences.getString("type", "").toString()
//            if(type2=="Donor"){
//                startActivity(Intent(this, Menu2::class.java))
//            }else{
//                startActivity(Intent(this, Menu::class.java))
//            }
//        }
//        binding.menuHome2.setOnClickListener {
//            startActivity(Intent(this, HomeActivity::class.java))
//        }
    }
}