package com.mad.neighbourlytest.activites.dinidu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.mad.neighbourlytest.R

class EditFamilyActivity : AppCompatActivity() {
    private var updatedFamilyName: String? = null
    private var updatedNoOfMembers: String? = null
    private var updatedAddress: String? = null
    private var updatedConNumber: String? = null
    private var updatedJobTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_family)

        var fieldsChanged = false


        // Get the data from the intent
        val familyName = intent.getStringExtra("family_name")
        val noOfMembers = intent.getStringExtra("Members")
        val address = intent.getStringExtra("faAddress")
        val conNumber = intent.getStringExtra("conNumbers")
        val jobTitle = intent.getStringExtra("jobTitle")

        val familyID = intent.getStringExtra("familyRegid")

        // Find the TextViews
        val familyNameTextView: TextView = findViewById(R.id.famName)
        val familyMembersTextView: TextView = findViewById(R.id.famNoOfMembers)
        val familyConNumberTextView: TextView = findViewById(R.id.famContact)
        val familyAddressTextView: TextView = findViewById(R.id.famAddress)
        val familyJobTextView: TextView = findViewById(R.id.famJob)

        // Set the text and disable the TextViews
        familyNameTextView.text = familyName
        familyMembersTextView.text = noOfMembers
        familyConNumberTextView.text = conNumber
        familyAddressTextView.text = address
        familyJobTextView.text = jobTitle

        // Disable the TextViews
        familyNameTextView.isEnabled = false
        familyMembersTextView.isEnabled = false
        familyConNumberTextView.isEnabled = false
        familyAddressTextView.isEnabled = false
        familyJobTextView.isEnabled = false

        // Find the update and delete buttons
        val bttnUpdate: Button = findViewById(R.id.addFamBtn)
        val bttnDelete: Button = findViewById(R.id.deleteFamBtn2)

        val db = FirebaseDatabase.getInstance()

        // Set a click listener for the update button
        bttnUpdate.setOnClickListener {
            // Enable the TextViews
            familyNameTextView.isEnabled = true
            familyMembersTextView.isEnabled = true
            familyConNumberTextView.isEnabled = true
            familyAddressTextView.isEnabled = true
            familyJobTextView.isEnabled = true

            // Save the updated data
            val updatedFamilyName = familyNameTextView.text.toString()
            val updatedNoOfMembers = familyMembersTextView.text.toString()
            val updatedConNumber = familyConNumberTextView.text.toString()
            val updatedAddress = familyAddressTextView.text.toString()
            val updatedJobTitle = familyJobTextView.text.toString()

            //update values in database

            familyNameTextView.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    fieldsChanged = true
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            familyMembersTextView.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    fieldsChanged = true
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            familyConNumberTextView.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    fieldsChanged = true
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            familyAddressTextView.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    fieldsChanged = true
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            familyJobTextView.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    fieldsChanged = true
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            if (fieldsChanged) {

                val recordRef = db.getReference("Family").child(familyID.toString())

                val updates = hashMapOf<String, Any>(
                    "familyName" to updatedFamilyName,
                    "noMembers" to updatedNoOfMembers,
                    "familyConNumber" to updatedConNumber,
                    "familyAddress" to updatedAddress,
                    "familyJob" to updatedJobTitle
                )

                // Update the record
                recordRef.updateChildren(updates)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Data Updated Successfully", Toast.LENGTH_LONG).show()
                        familyNameTextView.isEnabled = false
                        familyMembersTextView.isEnabled = false
                        familyConNumberTextView.isEnabled = false
                        familyAddressTextView.isEnabled = false
                        familyJobTextView.isEnabled = false
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"Failed to update data", Toast.LENGTH_LONG).show()
                    }
            }
        }

        bttnDelete.setOnClickListener{


            val recordRef = db.getReference("Family").child(familyID.toString())

            recordRef.removeValue()
                .addOnSuccessListener {
                    Toast.makeText(this,"Record deleted successfully", Toast.LENGTH_LONG).show()

                    val intent = Intent(this,FamilyListFetch::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Failed to delete record", Toast.LENGTH_LONG).show()
                }


        }
    }
}