package com.mad.neighbourlytest.activites.isuru

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.activites.dinidu.AddFamilyActivity
import com.mad.neighbourlytest.activites.dinidu.FamilyListFetch
import com.mad.neighbourlytest.activites.dinidu.FetchMessage
import com.mad.neighbourlytest.activites.ishara.Donate0Activity
import com.mad.neighbourlytest.activites.ishara.DonationCategory
import com.mad.neighbourlytest.activites.ishara.ItemDispatchedFetch
import com.mad.neighbourlytest.activites.ishara.ItemDonationActivity
import com.mad.neighbourlytest.activites.ishara.ItemDonationListFetch
import com.mad.neighbourlytest.activites.yasiru.AddArticleActivity
import com.mad.neighbourlytest.activites.yasiru.Articles
import com.mad.neighbourlytest.activites.yasiru.MyArticles
import com.mad.neighbourlytest.databinding.ActivityMenuAdminBinding

class MenuAdmin : AppCompatActivity() {

    private lateinit var binding: ActivityMenuAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuAdminBinding.inflate(layoutInflater)
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
            startActivity(Intent(this, FamilyListFetch::class.java))
        }
        binding.itemDonationListBtn.setOnClickListener {
            startActivity(Intent(this, ItemDonationListFetch::class.java))
        }
        binding.addArticleBtn.setOnClickListener {
            startActivity(Intent(this, AddArticleActivity::class.java))
        }
        binding.itemDispatchedListBtn.setOnClickListener {
            startActivity(Intent(this, ItemDispatchedFetch::class.java))
        }
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
            startActivity(Intent(this, MenuAdmin::class.java))
        }
        binding.menuHome2.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        binding.viewContactUs.setOnClickListener {
            startActivity(Intent(this, FetchMessage::class.java))
        }

    }
}