package com.mad.neighbourlytest.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.activites.dinidu.EditMessageActivity
import com.mad.neighbourlytest.models.contactUs

// Adapter class for the messages list
class messageAdapter (private val messageList: ArrayList<contactUs>) : RecyclerView.Adapter<messageAdapter.messageViewHolder>() {

    // Create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): messageViewHolder {
        // Inflate a layout from XML and return the holder
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_family_details_row, parent, false)
        return messageViewHolder(itemView)
    }

    // Return the size of the dataset
    override fun getItemCount(): Int {
        return messageList.size
    }

    // Replace the contents of a view
    override fun onBindViewHolder(mholder: messageViewHolder, position: Int) {
        val currentItem = messageList[position]

        // Set the user name
        mholder.name.text = currentItem.userName

        // Set a click listener for the view
        mholder.itemView.setOnClickListener {
            // Create an intent to edit a message
            val intent = Intent(mholder.itemView.context, EditMessageActivity::class.java)

            // Add the message data to the intent
            intent.putExtra("messageSubject",currentItem.subject)
            intent.putExtra("messageBody", currentItem.message)
            intent.putExtra("userEmail", currentItem.email)
            intent.putExtra("messageID",currentItem.messageID)

            // Start the edit activity
            mholder.itemView.context.startActivity(intent)
        }
    }

    // Provide a reference to the views for each data item
    class messageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Get the user name TextView
        val name: TextView = itemView.findViewById(R.id.uName)
    }
}
