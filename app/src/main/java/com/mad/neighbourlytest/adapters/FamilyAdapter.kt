package com.mad.neighbourlytest.adapters


import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.models.FamilyModel
import com.mad.neighbourlytest.activites.dinidu.EditFamilyActivity
import kotlin.math.log

class FamilyAdapter(private val familyList: ArrayList<FamilyModel>) : RecyclerView.Adapter<FamilyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_family_details_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return familyList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = familyList[position]
        holder.name.text = currentItem.familyName

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

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.uName)
    }
}
