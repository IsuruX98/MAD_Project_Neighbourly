package com.mad.neighbourlytest.activites.yasiru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mad.neighbourlytest.databinding.ActivityAddArticleBinding
import com.mad.neighbourlytest.models.yasiru.ArticleModel


class AddArticleActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference
    private lateinit var binding : ActivityAddArticleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.postArticleBtn.setOnClickListener{
            insertData()
        }
    }

    fun insertData(){
        val subject = binding.subjectArticle.text.toString()
        val description = binding.articleDescription.text.toString()

        if (subject.isEmpty()) {
            binding.subjectArticle.error = "Subject is required"
            return
        }

        if (description.isEmpty()) {
            binding.articleDescription.error = "Description is required"
            return
        }

        db = FirebaseDatabase.getInstance().getReference("articles")

        val article = ArticleModel(subject, description)
        val databaseReference = FirebaseDatabase.getInstance().reference
        val id = databaseReference.push().key

        db.child(id.toString()).setValue(article).addOnSuccessListener {
            binding.subjectArticle.text.clear()
            binding.articleDescription.text.clear()

            Toast.makeText(this, "data inserted!" , Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            Toast.makeText(this, "data not inserted!", Toast.LENGTH_LONG).show()
        }
    }
}