package com.mad.neighbourlytest.activites.dinidu

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
import com.mad.neighbourlytest.adapters.messageAdapter
import com.mad.neighbourlytest.models.contactUs

class FetchMessage : AppCompatActivity() {

    // Declare RecyclerView and data structures to store the fetched data
    private lateinit var messageRecyclerView: RecyclerView
    private lateinit var messageList:ArrayList<contactUs>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_message)

        // Initialize RecyclerView and set its layout manager
        messageRecyclerView = findViewById(R.id.recycleViewMessage)
        messageRecyclerView.layoutManager= LinearLayoutManager(this)
        messageRecyclerView.setHasFixedSize(true)

        // Initialize the list to store the fetched messages
        messageList = arrayListOf<contactUs>()

        // Call the method to get the message list from Firebase
        getMessageList()
    }

    // Method to fetch the message list from Firebase
    private fun getMessageList(){

        // Initially, hide the RecyclerView
        messageRecyclerView.visibility= View.GONE

        // Get a reference to the "Message" node in the Firebase database
        dbRef = FirebaseDatabase.getInstance().getReference("Message")

        // Attach a ValueEventListener to the database reference
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Clear the list before adding new messages
                messageList.clear()

                // Check if there are any messages in the database
                if(snapshot.exists()){

                    // Loop through all the messages and add them to the list
                    for (message in  snapshot.children){
                        val messageData = message.getValue(contactUs::class.java)
                        messageList.add(messageData!!)
                    }

                    // Create a new adapter for the RecyclerView and set it
                    val mAdapter = messageAdapter(messageList)
                    messageRecyclerView.adapter = mAdapter

                    // Make the RecyclerView visible
                    messageRecyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle any errors that occur during database retrieval
                // (not implemented in this code)
            }
        })
    }
}
