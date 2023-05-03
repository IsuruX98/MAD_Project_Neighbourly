package com.mad.neighbourlytest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.models.ItemDonationModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


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
        private val deleteButton : Button = itemView.findViewById(R.id.deleteBtn)
        private val dispatchButton : Button = itemView.findViewById(R.id.dispatchedBtnDonation)

        private fun deleteItem(id: String){
            val database : DatabaseReference = FirebaseDatabase.getInstance().getReference("Donation Items").child(id)
            val task = database.removeValue()
            task.addOnSuccessListener {
                SweetAlertDialog(itemView.context, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Success!!")
                    .setContentText("Item deleted Successfully")
                    .setConfirmClickListener {
                            sDialog: SweetAlertDialog -> sDialog.dismissWithAnimation()
                    }.show()
            }
            task.addOnFailureListener { error ->
                SweetAlertDialog(itemView.context, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Error!!")
                    .setContentText("Deleting Error ${error.message}")
                    .setConfirmClickListener {
                            sDialog: SweetAlertDialog -> sDialog.dismissWithAnimation()
                    }.show()
            }
        }
        init {
            deleteButton.setOnClickListener {
                SweetAlertDialog(itemView.context, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Delete Item")
                    .setContentText("Are you sure you want to delete this item?")
                    .setConfirmText("Yes")
                    .setConfirmClickListener { sDialog: SweetAlertDialog ->
                        deleteItem()
                        sDialog.dismissWithAnimation()
                    }
                    .setCancelText("No")
                    .setCancelClickListener { sDialog: SweetAlertDialog ->
                        sDialog.cancel()
                    }
                    .show()
            }
            dispatchButton.setOnClickListener {

            }


        }

    }

}