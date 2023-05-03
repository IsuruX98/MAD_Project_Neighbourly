package com.mad.neighbourlytest.activites.ishara


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.adapters.ItemAdapter
import com.mad.neighbourlytest.models.ItemDonationModel

class ItemDonationListFetch : AppCompatActivity() {

    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var itemList : ArrayList<ItemDonationModel>
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation_list)


        itemRecyclerView = findViewById(R.id.itemRecycler)
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)

        itemList = arrayListOf<ItemDonationModel>()

        getDonationItemData()
    }

    private fun getDonationItemData(){
        itemRecyclerView.visibility = View.GONE

        database = FirebaseDatabase.getInstance().getReference("Donation Items")

        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear()
                if(snapshot.exists()){
                    for(items in snapshot.children){

                        val itemData = items.getValue(ItemDonationModel::class.java)
                        itemList.add(itemData!!)
                    }

                    val mAdapter = ItemAdapter(itemList)
                    itemRecyclerView.adapter = mAdapter

                    itemRecyclerView.visibility = View.VISIBLE

                }else{
                    Log.d("my-tag","mukuth na hehe")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}