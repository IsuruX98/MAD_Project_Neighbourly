package com.mad.neighbourlytest.activites.yasiru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.activites.isuru.HomeActivity
import com.mad.neighbourlytest.models.ArticleModel

class Article : AppCompatActivity() {

    private lateinit var subject: TextView
    private lateinit var id: TextView
    private lateinit var description: TextView
    private lateinit var btnEdit: Button
    private lateinit var btnDelete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        id = findViewById(R.id.articleId)
        subject = findViewById(R.id.articleViewSubject)
        description = findViewById(R.id.viewArticleDescription2)
        btnEdit = findViewById(R.id.btnUpdateArticle)
        btnDelete = findViewById(R.id.btnDeleteArticle)


        //set values to views
        id.text = intent.getStringExtra("id")
        subject.text = intent.getStringExtra("subject")
        description.text = intent.getStringExtra("description")

        findViewById<ImageView>(R.id.menuHome2).setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


        btnEdit.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("Id").toString(),
                intent.getStringExtra("subject").toString(),
                intent.getStringExtra("description").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("Id").toString()
            )
        }
    }


    private fun openUpdateDialog(
        ID: String,
        etsubject: String,
        etdescription: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_edit_article, null)

        mDialog.setView(mDialogView)

        val IDs = mDialogView.findViewById<EditText>(R.id.editArticleId)
        val subjects = mDialogView.findViewById<EditText>(R.id.editArticleSubject)
        val descriptions = mDialogView.findViewById<EditText>(R.id.editArticleDescription)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.editArticleUpdateBtn)

        IDs.setText(intent.getStringExtra("Id").toString())
        subjects.setText(intent.getStringExtra("subject").toString())
        descriptions.setText(intent.getStringExtra("description").toString())

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            val subjectText = subjects.text.toString()
            val descriptionText = descriptions.text.toString()

            if (subjectText.isEmpty()) {
                Toast.makeText(applicationContext, "Subject is required", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (descriptionText.isEmpty()) {
                Toast.makeText(applicationContext, "Description is required", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            updateArticle(
                ID,
                subjectText,
                descriptionText
            )

            //we are setting updated data to our textviews
            subject.text = subjectText
            description.text = descriptionText

            alertDialog.dismiss()
        }
    }

    private fun updateArticle(
        iD: String,
        subject: String,
        description: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("articles").child(iD)
        val artInfo = ArticleModel(iD, subject, description)

        dbRef.setValue(artInfo)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Article updated", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { error ->
                Toast.makeText(
                    applicationContext,
                    "Article Not updated.${error}",
                    Toast.LENGTH_LONG
                ).show()
            }
    }


    private fun deleteRecord(
        iD: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("articles").child(iD)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Article Deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MyArticles::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }


}