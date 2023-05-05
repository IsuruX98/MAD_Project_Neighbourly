package com.mad.neighbourlytest.activites.yasiru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.adapters.ArticleAdapter
import com.mad.neighbourlytest.models.ArticleModel

class MyArticles : AppCompatActivity() {


    private lateinit var recyclerView : RecyclerView
    private lateinit var articles : ArrayList<ArticleModel>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_articles)

        recyclerView = findViewById(R.id.itemRecycl)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        articles = arrayListOf<ArticleModel>()

        getArticles()

    }

    private fun getArticles(){
        recyclerView.visibility = View.GONE


        dbRef = FirebaseDatabase.getInstance().getReference("articles")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                articles.clear()
                if(snapshot.exists()){
                    for(articleSnap in snapshot.children){
                        val articleData = articleSnap.getValue(ArticleModel::class.java)
                        articles.add(articleData!!)
                    }
                    val mAdapter = ArticleAdapter(articles)
                    recyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListner(object : ArticleAdapter.OnItemClickListner{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@MyArticles, Article::class.java)
                            intent.putExtra("Id",articles[position].articleId)
                            intent.putExtra("description", articles[position].description)
                            intent.putExtra("subject", articles[position].subject)

                            startActivity(intent)
                        }

                    })

                    recyclerView.visibility = View.VISIBLE

                }else{
                    Log.d("My-snap","mukuth na")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}