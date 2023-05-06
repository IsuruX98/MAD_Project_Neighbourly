package com.mad.neighbourlytest.activites.dinidu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.mad.neighbourlytest.R
import com.google.firebase.database.FirebaseDatabase
import com.mad.neighbourlytest.models.FamilyModel

class AddFamilyActivity : AppCompatActivity() {

    // Declare variables for EditText and Button
    private lateinit var famName:EditText
    private lateinit var famMembers:EditText
    private lateinit var famAddress:EditText
    private lateinit var contactNo:EditText
    private lateinit var famJob:EditText
    private lateinit var add:Button

    // Declare a Firebase database reference
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_family)

        // Initialize EditText and Button variables with respective view IDs
        famName=findViewById(R.id.famName)
        famMembers=findViewById(R.id.famNoOfMembers)
        famAddress=findViewById(R.id.famAddress)
        contactNo =findViewById(R.id.famContact)
        famJob =findViewById(R.id.famJob)
        add= findViewById(R.id.addFamBtn)

        // Initialize the Firebase database reference
        dbRef = FirebaseDatabase.getInstance().getReference("Family")

        // Set an OnClickListener on the "add" Button
        add.setOnClickListener{
            // Call the saveFamily() method
            saveFamily()
        }
    }

    // Method to save family data to Firebase Realtime Database
    private fun saveFamily(){

        // Retrieve user data from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val email2 = sharedPreferences.getString("email", "").toString()
        val name2 = sharedPreferences.getString("name", "").toString()

        // Get user inputs from EditText fields
        val familyName = famName.text.toString()
        val noMembers = famMembers.text.toString()
        val familyAddress =famAddress.text.toString()
        val familyConNumber = contactNo.text.toString()
        val familyJob = famJob.text.toString()

        // Check if any of the EditText fields are empty and display appropriate error message
        if(familyName.isEmpty()){
            famName.error ="Please Enter Name"
            return
        }
        if(noMembers.isEmpty()){
            famMembers.error ="Please Enter Number of Family Members"
            return
        }
        if(familyAddress.isEmpty()){
            famAddress.error ="Please Enter Family Address"
            return
        }
        if(familyConNumber.isEmpty()){
            contactNo.error="Please Enter Contact Number"
            return
        }
        if(familyJob.isEmpty()){
            famJob.error="Please Enter Job"
            return
        }

        // Generate a unique ID for the new family entry in the database
        val  familyID =dbRef.push().key!!

        // Create a new FamilyModel object with user input data
        val family = FamilyModel(familyID,familyName,noMembers,familyAddress,familyConNumber,familyJob,email2)

        // Add the new family entry to the Firebase database
        dbRef.child(familyID).setValue(family)
            .addOnSuccessListener{
                // Display a success message and clear the EditText fields
                Toast.makeText(this,"Data Inserted Successfully",Toast.LENGTH_LONG).show()
                famName.text.clear()
                famMembers.text.clear()
                famAddress.text.clear()
                contactNo.text.clear()
                famJob.text.clear()
            }
            .addOnFailureListener{ err ->
                Toast.makeText(this,"Data failed ${err.message}",Toast.LENGTH_LONG).show()
            }

    }
}