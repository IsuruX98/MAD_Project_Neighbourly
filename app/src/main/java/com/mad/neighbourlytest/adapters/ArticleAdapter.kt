package com.mad.neighbourlytest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.activites.yasiru.MyArticles
import com.mad.neighbourlytest.models.yasiru.ArticleModel

class ArticleAdapter (private val articles : ArrayList<ArticleModel>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val articleView = LayoutInflater.from(parent.context).inflate(R.layout.activity_one_article, parent, false)

        return ViewHolder(articleView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentArticle = articles[position]
        holder.subject.text = currentArticle.subject
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subject : TextView = itemView.findViewById(R.id.articleViewSubject)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

}