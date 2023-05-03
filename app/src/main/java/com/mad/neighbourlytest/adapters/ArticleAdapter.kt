package com.mad.neighbourlytest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.models.yasiru.ArticleModel

class ArticleAdapter (private val articles : ArrayList<ArticleModel>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>(){

    private lateinit var myListner : OnItemClickListner

    interface OnItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListner: OnItemClickListner){
        myListner = clickListner
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val articleView = LayoutInflater.from(parent.context).inflate(R.layout.activity_one_article, parent, false)
        return ViewHolder(articleView, myListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentArticle = articles[position]
        holder.subject.text = currentArticle.subject
        holder.description.text = currentArticle.description
    }

    class ViewHolder(itemView: View, clickListner: OnItemClickListner) : RecyclerView.ViewHolder(itemView) {
        val subject : TextView = itemView.findViewById(R.id.articleViewSubject)
        val description : TextView = itemView.findViewById(R.id.viewArticleDescription2)

        init{
            itemView.setOnClickListener{
                clickListner.onItemClick(adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

}