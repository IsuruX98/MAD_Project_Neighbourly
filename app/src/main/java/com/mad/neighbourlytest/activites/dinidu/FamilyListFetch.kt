package com.mad.neighbourlytest.activites.dinidu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.adapters.familyAdapter
import com.mad.neighbourlytest.models.ishara.FamilyModel

class FamilyListFetch : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var familyList:ArrayList<FamilyModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_family_list_fetch)

        userRecyclerView = findViewById(R.id.recycleView)
        userRecyclerView.layoutManager= LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        familyList = arrayListOf<FamilyModel>()

        getFamilyList()

    }

    private fun getFamilyList(){

        userRecyclerView.visibility= View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("Family")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                familyList.clear()
                if(snapshot.exists()){

                    for (user in  snapshot.children){

                        val familyData = user.getValue(FamilyModel::class.java)

                        familyList.add(familyData!!)
                    }
                    val fAdapter = familyAdapter(familyList)
                    userRecyclerView.adapter = fAdapter
                    userRecyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })




    }


}