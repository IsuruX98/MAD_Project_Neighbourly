package com.mad.neighbourlytest.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.play.integrity.internal.z
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.activites.ishara.ItemDonationActivity
import com.mad.neighbourlytest.activites.ishara.ThankYouActivity
import com.mad.neighbourlytest.models.ItemDonationModel
import org.checkerframework.checker.units.qual.Current


class ItemAdapter (private val itemDonateList : ArrayList<ItemDonationModel>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_donation_list_one,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemDonateList[position]
        holder.typeDonation.text = currentItem.typeDonation
        holder.qtyDonation.text = currentItem.quantityDonation
        holder.contactNameDonation.text = currentItem.contactName
        holder.contactNumDonation.text = currentItem.contactNum
        holder.donationID.text = currentItem.donationID


        if (currentItem.expDonation != "") {
            holder.expDonation.text = currentItem.expDonation
        } else {
            holder.expDonation.text = "No Expiry"
        }

        if(currentItem.dispatched){
            holder.itemDispatched.text = "Item Dispatched"
            holder.dispatchButton.visibility = View.GONE

        }else{
            holder.itemDispatched.text = "Item Not Yet Dispatched"
        }



    }

    override fun getItemCount(): Int {
        return itemDonateList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val donationID : TextView = itemView.findViewById(R.id.DonationID)
        val typeDonation : TextView = itemView.findViewById(R.id.inputTypeDonation)
        val qtyDonation : TextView = itemView.findViewById(R.id.inputQty)
        val expDonation : TextView = itemView.findViewById(R.id.inputExp)
        val contactNameDonation : TextView = itemView.findViewById(R.id.inputContactName)
        val contactNumDonation : TextView = itemView.findViewById(R.id.inputContactNum)
        val itemDispatched : TextView = itemView.findViewById(R.id.itemDispatched)
         private val deleteButton : Button = itemView.findViewById(R.id.deleteBtn)
        val dispatchButton : Button = itemView.findViewById(R.id.dispatchedBtnDonation)


        private fun deleteItem(id: String){

            val database : DatabaseReference = FirebaseDatabase.getInstance().getReference("Donation Items")
            val task = database.child(id).removeValue()
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

        private fun dispatchItem(id: String){

            val database : DatabaseReference = FirebaseDatabase.getInstance().getReference("Donation Items")
            val newValue = true

            val task = database.child(id).child("dispatched").setValue(newValue)
            task.addOnSuccessListener {

                SweetAlertDialog(itemView.context, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Success!!")
                    .setContentText("Item Dispatched Successfully")
                    .setConfirmClickListener {
                            sDialog: SweetAlertDialog -> sDialog.dismissWithAnimation()

                    }.show()
            }
            task.addOnFailureListener { error ->
                Log.d("Debug", "Failed to update item dispatch status: ${error.message}")
                SweetAlertDialog(itemView.context, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Error!!")
                    .setContentText("Dispatch Error ${error.message}")
                    .setConfirmClickListener {
                            sDialog: SweetAlertDialog -> sDialog.dismissWithAnimation()
                    }.show()
            }
        }


        init {
            deleteButton.setOnClickListener {
                val textID = donationID.text.toString()
                SweetAlertDialog(itemView.context, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Delete Item")
                    .setContentText("Are you sure you want to delete this item?")
                    .setConfirmText("Yes")
                    .setConfirmClickListener { sDialog: SweetAlertDialog ->
                        deleteItem(textID)
                        sDialog.dismissWithAnimation()
                    }
                    .setCancelText("No")
                    .setCancelClickListener { sDialog: SweetAlertDialog ->
                        sDialog.cancel()
                    }
                    .show()
            }
            dispatchButton.setOnClickListener {
                val textID = donationID.text.toString()
                SweetAlertDialog(itemView.context, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Dispatch Item")
                    .setContentText("Is this Item Dispatched?")
                    .setConfirmText("Yes")
                    .setConfirmClickListener { sDialog: SweetAlertDialog ->
                        dispatchItem(textID)
                        sDialog.dismissWithAnimation()
                    }
                    .setCancelText("No")
                    .setCancelClickListener { sDialog: SweetAlertDialog ->
                        sDialog.cancel()
                    }
                    .show()
            }


        }

    }

}