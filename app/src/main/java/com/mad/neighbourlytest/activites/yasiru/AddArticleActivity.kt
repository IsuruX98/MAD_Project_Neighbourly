package com.mad.neighbourlytest.activites.yasiru

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mad.neighbourlytest.databinding.ActivityAddArticleBinding
import com.mad.neighbourlytest.models.yasiru.ArticleModel

import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.activites.isuru.HomeActivity
import com.mad.neighbourlytest.activites.isuru.Menu
import com.mad.neighbourlytest.activites.isuru.Menu2

class AddArticleActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference
    private lateinit var binding : ActivityAddArticleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_article)
//        binding.menuHome.setOnClickListener {
//            //making a sharedPreference to access in the app
//            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//            val type2 = sharedPreferences.getString("type", "").toString()
//            if(type2=="Donor"){
//                startActivity(Intent(this, Menu2::class.java))
//            }else{
//                startActivity(Intent(this, Menu::class.java))
//            }
//        }
//        binding.menuHome2.setOnClickListener {
//            startActivity(Intent(this, HomeActivity::class.java))
//        }
        binding = ActivityAddArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.postArticleBtn.setOnClickListener{
            insertData()
        }
    }

    fun insertData(){
        val subject = binding.subjectArticle.text.toString()
        val description = binding.articleDescription.text.toString()

        //making a sharedPreference to access in the app
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val name2 = sharedPreferences.getString("name", "").toString()
        val email2 = sharedPreferences.getString("email", "").toString()

        if (subject.isEmpty()) {
            binding.subjectArticle.error = "Subject is required"
            return
        }

        if (description.isEmpty()) {
            binding.articleDescription.error = "Description is required"
            return
        }

        db = FirebaseDatabase.getInstance().getReference("articles")



        val databaseReference = FirebaseDatabase.getInstance().reference
        val id = databaseReference.push().key!!
        val article = ArticleModel(id,email2, subject, description)

        db.child(id).setValue(article).addOnSuccessListener {
            binding.subjectArticle.text.clear()
            binding.articleDescription.text.clear()

            Toast.makeText(this, "data inserted!" , Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            Toast.makeText(this, "data not inserted!", Toast.LENGTH_LONG).show()
        }
    }
}