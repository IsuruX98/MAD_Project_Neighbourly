package com.mad.neighbourlytest.activites.ishara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.mad.neighbourlytest.R

class ItemDonationListOne : AppCompatActivity() {

    private lateinit var deleteBtn : Button
    private lateinit var dispatchedBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_donation_list_one)

        deleteBtn = findViewById(R.id.deleteBtn)
        dispatchedBtn = findViewById(R.id.dispatchedBtnDonation)

        deleteBtn.setOnClickListener {
            Toast.makeText(this, "delete works", Toast.LENGTH_SHORT).show()
        }
        dispatchedBtn.setOnClickListener {
            Toast.makeText(this, "items dispatched", Toast.LENGTH_SHORT).show()
        }

    }
}