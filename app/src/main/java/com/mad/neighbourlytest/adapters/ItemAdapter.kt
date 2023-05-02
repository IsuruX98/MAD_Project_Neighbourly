package com.mad.neighbourlytest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.models.ItemDonationModel

class ItemAdapter (private val itemDonateList : ArrayList<ItemDonationModel>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_donation_list_one,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemDonateList[position]
        holder.typeDonation.text = currentItem.typeDonation
        holder.qtyDonation.text = currentItem.quantityDonation
        holder.expDonation.text = currentItem.expDonation
        holder.contactNameDonation.text = currentItem.contactName
        holder.contactNumDonation.text = currentItem.contactNum
        println(currentItem)
    }

    override fun getItemCount(): Int {
        return itemDonateList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val typeDonation : TextView = itemView.findViewById(R.id.inputTypeDonation)
        val qtyDonation : TextView = itemView.findViewById(R.id.inputQty)
        val expDonation : TextView = itemView.findViewById(R.id.inputExp)
        val contactNameDonation : TextView = itemView.findViewById(R.id.inputContactName)
        val contactNumDonation : TextView = itemView.findViewById(R.id.inputContactNum)

    }

}