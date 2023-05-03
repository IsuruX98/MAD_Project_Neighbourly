package com.mad.neighbourlytest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.models.ishara.FamilyModel

class familyAdapter (private val familyList:ArrayList<FamilyModel>): RecyclerView.Adapter<familyAdapter.myViewHolder>(){

    private lateinit var  mListner:OnItemClickListner

    interface OnItemClickListner{

        fun onItemClick(position: Int)
    }
    fun setOnItemClickListner(clickListner: OnItemClickListner){

        mListner = clickListner
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.activity_family_details_row,parent,false)
        return myViewHolder(itemView,mListner)
    }

    override fun getItemCount(): Int {
        return familyList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentItem =familyList[position]

        holder.Name.text =currentItem.familyName

    }
    class myViewHolder(itemView: View, clickListner: OnItemClickListner): RecyclerView.ViewHolder(itemView){


        val Name: TextView =itemView.findViewById(R.id.uName)

        init {

            itemView.setOnClickListener{

                clickListner.onItemClick(adapterPosition)
            }
        }
    }


}
