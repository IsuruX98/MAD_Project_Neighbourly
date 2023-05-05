package com.mad.neighbourlytest.activites.ishara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mad.neighbourlytest.R

class ItemDonationListOne : AppCompatActivity() {
    private lateinit var dispatchBtn : Button
    private lateinit var deleteBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_donation_list_one)

        dispatchBtn = findViewById(R.id.dispatchedBtnDonation)
        deleteBtn = findViewById(R.id.deleteBtn)


    }
}