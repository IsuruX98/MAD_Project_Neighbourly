package com.mad.neighbourlytest.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.models.FamilyModel
import com.mad.neighbourlytest.activites.dinidu.EditFamilyActivity

class FamilyAdapter(private val familyList: ArrayList<FamilyModel>) : RecyclerView.Adapter<FamilyAdapter.MyViewHolder>() {

    // Inflates the view holder with the layout for the list item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_family_details_row, parent, false)
        return MyViewHolder(itemView)
    }

    // Returns the total number of items in the list
    override fun getItemCount(): Int {
        return familyList.size
    }

    // Populates each list item with data from a FamilyModel object
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = familyList[position]
        holder.name.text = currentItem.familyName

        // Sets a click listener on the item that opens the EditFamilyActivity with data from the current item
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, EditFamilyActivity::class.java)
            intent.putExtra("familyRegid",currentItem.familyID)
            intent.putExtra("family_name", currentItem.familyName)
            intent.putExtra("Members", currentItem.noMembers)
            intent.putExtra("faAddress", currentItem.familyAddress)
            intent.putExtra("conNumbers",currentItem.familyConNumber)
            intent.putExtra("jobTitle",currentItem.familyJob)
            holder.itemView.context.startActivity(intent)
        }
    }

    // Represents each list item and holds references to its views
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.uName)
    }
}
