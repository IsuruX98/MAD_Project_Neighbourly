package com.mad.neighbourlytest.activites.isuru

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.activites.yasiru.AddArticleActivity
import com.mad.neighbourlytest.activites.dinidu.AddFamilyActivity
import com.mad.neighbourlytest.activites.yasiru.Articles
import com.mad.neighbourlytest.activites.ishara.DonationCategory
import com.mad.neighbourlytest.activites.dinidu.FamilyList
import com.mad.neighbourlytest.activites.ishara.Donate0Activity
import com.mad.neighbourlytest.activites.ishara.ItemDonationActivity
import com.mad.neighbourlytest.activites.ishara.ItemDonationListFetch
import com.mad.neighbourlytest.activites.yasiru.MyArticles
import com.mad.neighbourlytest.databinding.ActivityMenu2Binding

class Menu2 : AppCompatActivity() {

    private lateinit var binding: ActivityMenu2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenu2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.articlesBtn.setOnClickListener {
            startActivity(Intent(this, Articles::class.java))
        }
        binding.donateBtn.setOnClickListener {
            startActivity(Intent(this, Donate0Activity::class.java))
        }
        binding.donateCategoryBtn.setOnClickListener {
            startActivity(Intent(this, DonationCategory::class.java))
        }
        binding.itemDonations.setOnClickListener {
            startActivity(Intent(this, ItemDonationActivity::class.java))
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