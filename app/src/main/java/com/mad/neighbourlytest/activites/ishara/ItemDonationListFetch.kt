package com.mad.neighbourlytest.activites.ishara


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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

class ItemDonationListFetch : AppCompatActivity() {

    //private properties
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var itemList : ArrayList<ItemDonationModel>
    private lateinit var database : DatabaseReference
    private lateinit var showEmpty : TextView
    private lateinit var homeBtn : ImageView
    private lateinit var menuBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation_list)


        itemRecyclerView = findViewById(R.id.itemRecycler)
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)
        homeBtn = findViewById(R.id.menuHome2)
        menuBtn = findViewById(R.id.menuHome)


        //show when only list is empty
        showEmpty = findViewById(R.id.emptyList)

        itemList = arrayListOf<ItemDonationModel>()
        //call function  to fetch data
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
        //wait till data load
        itemRecyclerView.visibility = View.GONE

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val typeUser = sharedPreferences.getString("type", "").toString()

        database = FirebaseDatabase.getInstance().getReference("Donation Items")
        //listen to changes and provide updates of database
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                itemList.clear()

                //fetch data
                if(snapshot.exists()){
                    for(items in snapshot.children){
                        val itemData = items.getValue(ItemDonationModel::class.java)

                        if (itemData != null) {
                            itemData.typeUser = typeUser
                            //check status in dispatched field
                            if(!itemData.dispatched){
                                itemList.add(itemData)

                            }
                        }

                    }

                    //create adapter with updated itemList
                    val mAdapter = ItemAdapter(itemList)

                    //add adapter for recycle view to display data
                    itemRecyclerView.adapter = mAdapter

                    itemRecyclerView.visibility = if (itemList.isEmpty()) View.GONE else View.VISIBLE
                    showEmpty.visibility = if (itemList.isEmpty()) View.VISIBLE else View.GONE

                }else{
                    showEmpty.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("error-Tag", "Database error occurred: ${error.message}")
                Toast.makeText(this@ItemDonationListFetch, "Database error occurred", Toast.LENGTH_SHORT).show()

            }

        })

    }
}