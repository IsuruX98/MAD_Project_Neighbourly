package com.mad.neighbourlytest.activites.dinidu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.mad.neighbourlytest.R

class EditMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_message)

        // get data passed from intent
        val UserEmail = intent.getStringExtra("userEmail")
        val MessageSubject = intent.getStringExtra("messageSubject")
        val Message = intent.getStringExtra("messageBody")
        val mID = intent.getStringExtra("messageID")

        // set text to respective views
        val userEmailTextView: TextView = findViewById(R.id.messageUEmail)
        val messageSubjectTextView: TextView = findViewById(R.id.messageSubject)
        val messageBody: TextView = findViewById(R.id.messageBody)

        userEmailTextView.text = UserEmail
        messageSubjectTextView.text = MessageSubject
        messageBody.text = Message

        // disable editing of text views
        userEmailTextView.isEnabled = false
        messageSubjectTextView.isEnabled = false
        messageBody.isEnabled = false

        // initialize button and Firebase database instance
        val btnDelete: Button = findViewById(R.id.deletemsgBtn2)
        val db = FirebaseDatabase.getInstance()

        btnDelete.setOnClickListener{
            // delete message from Firebase Realtime Database
            val recordRef = db.getReference("Message").child(mID.toString())

            recordRef.removeValue()
                .addOnSuccessListener {
                    Toast.makeText(this,"Record deleted successfully", Toast.LENGTH_LONG).show()

                    // redirect to FetchMessage activity after successful deletion
                    val intent = Intent(this,FetchMessage::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Failed to delete record", Toast.LENGTH_LONG).show()
                }
        }
    }
}
