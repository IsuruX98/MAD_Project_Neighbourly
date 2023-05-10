package com.mad.neighbourlytest.activites.ishara

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.activites.isuru.HomeActivity
import com.mad.neighbourlytest.activites.isuru.Menu
import com.mad.neighbourlytest.activites.isuru.Menu2
import com.mad.neighbourlytest.adapters.ItemAdapter
import com.mad.neighbourlytest.models.ItemDonationModel

class ItemDispatchedFetch: AppCompatActivity() {

    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var itemList : ArrayList<ItemDonationModel>
    private lateinit var database : DatabaseReference
    private lateinit var showEmpty : TextView
    private lateinit var homeBtn : ImageView
    private lateinit var menuBtn : ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_dispatched_fetch)

        homeBtn = findViewById(R.id.menuHome2)
        menuBtn = findViewById(R.id.menuHome)
        itemRecyclerView = findViewById(R.id.itemRecycler)
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        //set fixed size to recycler view
        itemRecyclerView.setHasFixedSize(true)
        showEmpty = findViewById(R.id.emptyList)

        itemList = arrayListOf<ItemDonationModel>()

        getDonationItemData()
        menuBtn.setOnClickListener {
            //making a sharedPreference to access in the app
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val type2 = sharedPreferences.getString("type", "").toString()
            if(type2=="Donor"){
                startActivity(Intent(this, Menu2::class.java))
            }else{
                startActivity(Intent(this, Menu::class.java))
            }
        }
        homeBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun getDonationItemData(){
        itemRecyclerView.visibility = View.GONE

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val typeUser = sharedPreferences.getString("type", "").toString()

        database = FirebaseDatabase.getInstance().getReference("Donation Items")

        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear()
                if(snapshot.exists()){
                    for(items in snapshot.children){

                        val itemData = items.getValue(ItemDonationModel::class.java)
                        if (itemData != null) {
                            itemData.typeUser = typeUser
                            if(itemData.dispatched){
                                itemList.add(itemData)
                            }
                        }

                    }

                    val mAdapter = ItemAdapter(itemList)
                    itemRecyclerView.adapter = mAdapter

                    itemRecyclerView.visibility = View.VISIBLE


                }else{
                    showEmpty.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("error-Tag", "Database error occurred: ${error.message}")
                Toast.makeText(this@ItemDispatchedFetch, "Database error occurred", Toast.LENGTH_SHORT).show()
            }

        })

    }
}