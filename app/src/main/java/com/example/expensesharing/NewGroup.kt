package com.example.expensesharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import javax.xml.xpath.XPathConstants.STRING

class NewGroup : AppCompatActivity() {
    private lateinit var grpName: EditText
    private lateinit var grpExpense: EditText
    private lateinit var btnCreate: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_group)

        grpName = findViewById(R.id.grpName)
        grpExpense = findViewById(R.id.grpExpense)
        btnCreate = findViewById(R.id.btnCreate)

        dbRef = FirebaseDatabase.getInstance().getReference("Groups")
        val userID = FirebaseAuth.getInstance().currentUser

        btnCreate.setOnClickListener {
            saveGroupData()
        }
    }

    private fun saveGroupData() {

        //getting values
        val grp_Name = grpName.text.toString()
        val grp_Expense = grpExpense.text.toString()
        val user_ID = FirebaseAuth.getInstance().currentUser?.uid

        if (grp_Name.isEmpty()) {
            grpName.error = "Please enter group name"
        }
        if (grp_Expense.isEmpty()) {
            grpExpense.error = "Please enter the total expenditure"
        }


        val grpId = dbRef.push().key!!

        val group = GroupData(grpId, grp_Name, grp_Expense, user_ID)

        dbRef.child(grp_Name).setValue(group)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                grpName.text.clear()
                grpExpense.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}