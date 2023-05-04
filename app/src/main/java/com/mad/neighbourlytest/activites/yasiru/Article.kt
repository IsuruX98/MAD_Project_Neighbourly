package com.mad.neighbourlytest.activites.yasiru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.identity.AccessControlProfileId
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.models.yasiru.ArticleModel

class Article : AppCompatActivity() {

    private lateinit var subject : TextView
    private lateinit var id : TextView
    private lateinit var description :  TextView
    private lateinit var btnUpdate : Button
    private lateinit var btnDelete : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        id = findViewById(R.id.articleId)
        subject = findViewById(R.id.articleViewSubject)
        description = findViewById(R.id.viewArticleDescription2)
        btnUpdate = findViewById(R.id.btnUpdateArticle)

        setValuesToViews()


        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("Id").toString(),
                intent.getStringExtra("subject").toString(),
                intent.getStringExtra("description").toString()
            )
        }
    }


    private fun setValuesToViews(){
        id.text = intent.getStringExtra("id")
        subject.text = intent.getStringExtra("subject")
        description.text = intent.getStringExtra("description")
    }


    private fun openUpdateDialog(
        ID: String,
        etsubject: String,
        etdescription : String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_edit_article, null)

        mDialog.setView(mDialogView)

        val IDs = mDialogView.findViewById<EditText>(R.id.editArticleId)
        val subjects = mDialogView.findViewById<EditText>(R.id.editArticleSubject)
        val descriptions= mDialogView.findViewById<EditText>(R.id.editArticleDescription)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.editArticleUpdateBtn)

        IDs.setText(intent.getStringExtra("Id").toString())
        subjects.setText(intent.getStringExtra("subject").toString())
        descriptions.setText(intent.getStringExtra("description").toString())

        mDialog.setTitle("Updating $etsubject Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateArticle(
                ID,
                subjects.text.toString(),
                descriptions.text.toString()

            )
            System.out.println(ID)
            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews

            subject.text = subjects.text.toString()
            description.text = descriptions.text.toString()

            alertDialog.dismiss()
        }


    }

    private fun updateArticle(
        iD: String,
        subject: String,
        description: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(iD)
        val artInfo = ArticleModel(iD, subject, description)
        dbRef.setValue(artInfo)
    }






}