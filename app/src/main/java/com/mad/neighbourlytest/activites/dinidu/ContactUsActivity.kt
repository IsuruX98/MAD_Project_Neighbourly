package com.mad.neighbourlytest.activites.dinidu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mad.neighbourlytest.R
import com.mad.neighbourlytest.models.FamilyModel
import com.mad.neighbourlytest.models.contactUs
import javax.security.auth.Subject

class ContactUsActivity : AppCompatActivity() {

    private lateinit var Subject:EditText
    private lateinit var Message:EditText
    private lateinit var Send:Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        Subject= findViewById(R.id.subject)
        Message=findViewById(R.id.logEmail3)
        Send=findViewById(R.id.contactUsBtn)

        dbRef = FirebaseDatabase.getInstance().getReference("Message")

        Send.setOnClickListener{

            saveMessage()
        }
    }

    private fun saveMessage(){

        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val email2 = sharedPreferences.getString("email", "").toString()

        val messageSubject = Subject.text.toString()
        val messageBody = Message.text.toString()



        if(messageSubject.isEmpty()){

            Subject.error ="Please Enter Subject"
        }
        if(messageBody.isEmpty()){

            Message.error ="Please Enter Message"

        }

        val  messageID =dbRef.push().key!!
        val contactus = contactUs(messageSubject,messageBody,email2)

        dbRef.child(messageID).setValue(contactus)
            .addOnSuccessListener{
                Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()
                Subject.text.clear()
                Message.text.clear()

            }
            .addOnFailureListener{ err ->
                Toast.makeText(this,"Data failed ${err.message}", Toast.LENGTH_LONG).show()
            }

    }



}