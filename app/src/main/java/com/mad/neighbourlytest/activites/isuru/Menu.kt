package com.mad.neighbourlytest.activites.isuru

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.activites.dinidu.AddFamilyActivity
import com.mad.neighbourlytest.activites.dinidu.FamilyList
import com.mad.neighbourlytest.activites.ishara.Donate0Activity
import com.mad.neighbourlytest.activites.ishara.DonationCategory
import com.mad.neighbourlytest.activites.ishara.ItemDonationActivity
import com.mad.neighbourlytest.activites.ishara.ItemDonationListFetch
import com.mad.neighbourlytest.activites.yasiru.AddArticleActivity
import com.mad.neighbourlytest.activites.yasiru.Articles
import com.mad.neighbourlytest.activites.yasiru.MyArticles
import com.mad.neighbourlytest.databinding.ActivityMenuBinding

class Menu : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addArticleBtn.setOnClickListener {
            startActivity(Intent(this, AddArticleActivity::class.java))
        }
        binding.myArticleBtn.setOnClickListener {
            startActivity(Intent(this, MyArticles::class.java))
        }
        binding.addFamilyBtn.setOnClickListener {
            startActivity(Intent(this, AddFamilyActivity::class.java))
        }
        binding.familyListBtn.setOnClickListener {
            startActivity(Intent(this, FamilyList::class.java))
        }
        binding.itemDonationListBtn.setOnClickListener {
            startActivity(Intent(this, ItemDonationListFetch::class.java))
        }
        binding.donateBtn2.setOnClickListener {
            startActivity(Intent(this, Donate0Activity::class.java))
        }
        binding.donationCategoriesBtn.setOnClickListener {
            startActivity(Intent(this, DonationCategory::class.java))
        }
        binding.addArticleBtn.setOnClickListener {
            startActivity(Intent(this, AddArticleActivity::class.java))
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