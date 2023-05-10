package com.mad.neighbourlytest.activites.dinidu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.mad.neighbourlytest.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mad.neighbourlytest.activites.isuru.HomeActivity
import com.mad.neighbourlytest.activites.isuru.Menu
import com.mad.neighbourlytest.activites.isuru.Menu2
import com.mad.neighbourlytest.models.FamilyModel

class AddFamilyActivity : AppCompatActivity() {

    // Declare variables for EditText and Button
    private lateinit var famName:EditText
    private lateinit var famMembers:EditText
    private lateinit var famAddress:EditText
    private lateinit var contactNo:EditText
    private lateinit var famJob:EditText
    private lateinit var add:Button
    private lateinit var homeBtn : ImageView
    private lateinit var menuBtn : ImageView

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
        homeBtn = findViewById(R.id.menuHome2)
        menuBtn = findViewById(R.id.menuHome)

        // Initialize the Firebase database reference
        dbRef = FirebaseDatabase.getInstance().getReference("Family")

        // Set an OnClickListener on the "add" Button
        add.setOnClickListener{
            // Call the saveFamily() method
            saveFamily()
        }

        menuBtn.setOnClickListener {
            //making a sharedPreference to access in the app
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val type2 = sharedPreferences.getString("type", "").toString()
            if(type2=="Donor"){
                startActivity(Intent(this, Menu2::class.java))
            }else{
                startActivity(Intent(this, Menu::class.java))
            }
        }
        homeBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
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

        val phonePattern = Regex("^[+]?[0-9]{10,13}\$")
        if (!phonePattern.matches(familyConNumber)) {
            contactNo.error = "Please enter a valid phone number."
            return
        }
        val maxMembers = 10
        if (noMembers.toInt() > maxMembers) {
            famMembers.error = "Maximum $maxMembers family members allowed."
            return
        }
        val specialCharPattern = Regex("[^A-Za-z ]")
        if (specialCharPattern.containsMatchIn(familyName) || specialCharPattern.containsMatchIn(familyJob)) {
            Toast.makeText(this, "Please remove any special characters or digits from the input fields.", Toast.LENGTH_SHORT).show()
            return
        }


        val specialCharPattern2 = Regex("[^A-Za-z0-9 (,/)]")
        if (specialCharPattern2.containsMatchIn(familyAddress)) {
            Toast.makeText(this, "Please remove any special characters from the family address field except for ( and /.", Toast.LENGTH_SHORT).show()
            return
        }

        dbRef.orderByChild("familyName").equalTo(familyName).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    famName.error = "This family already exists in the database."
                    return
                }

            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AddFamilyActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })




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