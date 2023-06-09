package com.mad.neighbourlytest.activites.yasiru

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.activites.isuru.HomeActivity
import com.mad.neighbourlytest.activites.isuru.Menu
import com.mad.neighbourlytest.activites.isuru.Menu2
import com.mad.neighbourlytest.adapters.ArticleAdapter
import com.mad.neighbourlytest.models.ArticleModel

class MyArticles : AppCompatActivity() {


    private lateinit var recyclerView : RecyclerView
    private lateinit var articles : ArrayList<ArticleModel>
    private lateinit var dbRef : DatabaseReference
    private lateinit var noArticle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_articles)

        recyclerView = findViewById(R.id.itemRecycl)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        noArticle = findViewById(R.id.noArticles)

        articles = arrayListOf<ArticleModel>()

        getArticles()

        var home = findViewById<ImageView>(R.id.menuHome2)

        home.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getArticles() {
        recyclerView.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("articles")

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)


        val email2 = sharedPreferences.getString("email", "").toString()
        val type2 = sharedPreferences.getString("type", "").toString()

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                articles.clear()
                if (snapshot.exists()) {
                    for (articleSnap in snapshot.children) {
                        val articleData = articleSnap.getValue(ArticleModel::class.java)

                        // Get article data based on the user's email when the user is a volunteer
                        if (type2 == "Volunteer") {
                            if (articleData?.email == email2) {
                                articles.add(articleData!!)
                            }else{
                                noArticle.visibility = View.VISIBLE
                            }


                        }
                        else {
                            articles.add(articleData!!)
                        }

                        if(type2 == "Admin" || type2 == "Donor"){
                            val textView37 = findViewById<TextView>(R.id.textView37)
                            textView37.text = "Articles"

                        }

                    }
                    val mAdapter = ArticleAdapter(articles)
                    recyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListner(object : ArticleAdapter.OnItemClickListner {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@MyArticles, Article::class.java)
                            intent.putExtra("Id", articles[position].articleId)
                            intent.putExtra("description", articles[position].description)
                            intent.putExtra("subject", articles[position].subject)

                            startActivity(intent)
                        }

                    })

                    recyclerView.visibility = View.VISIBLE

                } else {
                    
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}