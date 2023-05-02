package com.mad.neighbourlytest.activites.isuru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.activites.yasiru.AddArticleActivity
import com.mad.neighbourlytest.activites.dinidu.AddFamilyActivity
import com.mad.neighbourlytest.activites.yasiru.Articles
import com.mad.neighbourlytest.activites.ishara.DonationCategory
import com.mad.neighbourlytest.activites.dinidu.FamilyList
import com.mad.neighbourlytest.activites.ishara.ItemDonationActivity
import com.mad.neighbourlytest.activites.ishara.ItemDonationList
import com.mad.neighbourlytest.activites.yasiru.MyArticles
import com.mad.neighbourlytest.databinding.ActivityMenu2Binding

class Menu2 : AppCompatActivity() {

    private lateinit var binding: ActivityMenu2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenu2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addArticleBtn.setOnClickListener {
            startActivity(Intent(this, AddArticleActivity::class.java))
        }
        binding.myArticleBtn.setOnClickListener {
            startActivity(Intent(this, MyArticles::class.java))
        }
        binding.articlesBtn.setOnClickListener {
            startActivity(Intent(this, Articles::class.java))
        }
        binding.addFamilyBtn.setOnClickListener {
            startActivity(Intent(this, AddFamilyActivity::class.java))
        }
        binding.familyListBtn.setOnClickListener {
            startActivity(Intent(this, FamilyList::class.java))
        }
        binding.donateBtn.setOnClickListener {
            startActivity(Intent(this, DonateActivity::class.java))
        }
        binding.donateCategoryBtn.setOnClickListener {
            startActivity(Intent(this, DonationCategory::class.java))
        }
        binding.itemDonationListBtn.setOnClickListener {
            startActivity(Intent(this, ItemDonationList::class.java))
        }
        binding.itemDonations.setOnClickListener {
            startActivity(Intent(this, ItemDonationActivity::class.java))
        }
        binding.userListBtn.setOnClickListener {
            startActivity(Intent(this, UserList::class.java))
        }
    }
}