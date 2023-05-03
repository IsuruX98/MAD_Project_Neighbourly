package com.mad.neighbourlytest.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.models.FamilyModel


class FamilyAdapter(private val familyList:ArrayList<FamilyModel>): RecyclerView.Adapter<FamilyAdapter.MyViewHolder>(){

//    private lateinit var  mListner:OnItemClickListner

//    interface OnItemClickListner{
//
//        fun onItemClick(position: Int)
//    }
//    fun setOnItemClickListner(clickListner: OnItemClickListner){
//        mListner = clickListner
//    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.activity_family_details_row,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return familyList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem =familyList[position]
        holder.name.text =currentItem.familyName


    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val name: TextView = itemView.findViewById(R.id.uName)

        init {

//            itemView.setOnClickListener{
//                clickListner.onItemClick(adapterPosition)
//            }
        }
    }


}
