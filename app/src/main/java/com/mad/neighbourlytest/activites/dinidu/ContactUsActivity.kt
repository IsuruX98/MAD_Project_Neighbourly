package com.mad.neighbourlytest.activites.dinidu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.models.contactUs

class ContactUsActivity : AppCompatActivity() {

    // UI elements
    private lateinit var subjectEditText: EditText
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: Button

    // Firebase
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        // Initialize UI elements
        subjectEditText = findViewById(R.id.subject)
        messageEditText = findViewById(R.id.logEmail3)
        sendButton = findViewById(R.id.contactUsBtn)

        // Get database reference
        dbRef = FirebaseDatabase.getInstance().getReference("Message")

        // Set click listener for send button
        sendButton.setOnClickListener{
            saveMessage()
        }
    }

    // Function to save message to Firebase database
    private fun saveMessage(){

        // Get user's email and name from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "").toString()
        val name = sharedPreferences.getString("name", "").toString()

        // Get message subject and body from UI elements
        val messageSubject = subjectEditText.text.toString()
        val messageBody = messageEditText.text.toString()

        // Validate message fields
        if(messageSubject.isEmpty()){
            subjectEditText.error ="Please Enter Subject"
            return
        }
        if(messageBody.isEmpty()){
            messageEditText.error ="Please Enter Message"
            return
        }

        // Generate unique ID for message
        val  messageID = dbRef.push().key!!

        // Create contactUs object with message details
        val contactUs = contactUs(messageID, messageSubject, messageBody, email, name)

        // Save contactUs object to database
        dbRef.child(messageID).setValue(contactUs)
            .addOnSuccessListener{
                // Show success message and clear UI elements
                Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_LONG).show()
                subjectEditText.text.clear()
                messageEditText.text.clear()
            }
            .addOnFailureListener{ err ->
                // Show failure message with error details
                Toast.makeText(this, "Data failed ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
