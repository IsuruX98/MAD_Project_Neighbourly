package com.mad.neighbourlytest.activites.yasiru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.mad.neighbourlytest.R

class Article : AppCompatActivity() {

    private lateinit var subject : TextView
    private lateinit var description :  TextView
    private lateinit var btnUpdate : Button
    private lateinit var btnDelete : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        initView()
        setValuesToViews()
    }

    private fun initView(){
        subject = findViewById(R.id.articleViewSubject)
        description = findViewById(R.id.viewArticleDescription2)


    }

    private fun setValuesToViews(){
        subject.text = intent.getStringExtra("subject")
        description.text = intent.getStringExtra("description")
    }


}