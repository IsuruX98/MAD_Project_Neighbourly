package com.mad.neighbourlytest.activites.dinidu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
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
import com.mad.neighbourlytest.adapters.FamilyAdapter
import com.mad.neighbourlytest.models.FamilyModel

class FamilyListFetch : AppCompatActivity() {

    // Declare variables
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var familyList:ArrayList<FamilyModel>
    private lateinit var dbRef: DatabaseReference
    private lateinit var homeBtn : ImageView
    private lateinit var menuBtn : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout
        setContentView(R.layout.activity_family_list_fetch)

        // Initialize variables
        homeBtn = findViewById(R.id.menuHome2)
        menuBtn = findViewById(R.id.menuHome)
        userRecyclerView = findViewById(R.id.recycleView)
        userRecyclerView.layoutManager= LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        familyList = arrayListOf<FamilyModel>()

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

        // Call the function to fetch the family list
        getFamilyList()
    }



    // Function to fetch family list from Firebase Realtime Database
    private fun getFamilyList(){

        // Hide the RecyclerView until data is fetched
        userRecyclerView.visibility= View.GONE

        // Get a reference to the "Family" node in the database
        dbRef = FirebaseDatabase.getInstance().getReference("Family")

        // Attach a listener to retrieve the data from the database
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                // Clear the familyList so it doesn't contain any duplicate data
                familyList.clear()

                if(snapshot.exists()){
                    // Iterate through the database snapshot and add each family to the familyList
                    for (user in  snapshot.children){
                        val familyData = user.getValue(FamilyModel::class.java)
                        familyList.add(familyData!!)
                    }

                    // Create an adapter with the familyList and set it to the RecyclerView
                    val fAdapter = FamilyAdapter(familyList)
                    userRecyclerView.adapter = fAdapter

                    // Show the RecyclerView now that data has been fetched
                    userRecyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors if data retrieval is cancelled
                Log.e("FamilyListFetch", "Error retrieving data: ${error.message}")
            }
        })
    }
}
