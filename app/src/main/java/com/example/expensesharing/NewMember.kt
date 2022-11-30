package com.example.expensesharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.net.IDN

class NewMember : AppCompatActivity() {

    private lateinit var memName: EditText
    private lateinit var memExpense: EditText
    private lateinit var mailID: EditText
    private lateinit var Shares: EditText
    private lateinit var Amount: EditText

    private lateinit var btnCreate: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_member)


        memName = findViewById(R.id.memName)
        memExpense = findViewById(R.id.memExpense)
        mailID = findViewById(R.id.mailID)
        Amount = findViewById(R.id.Amount)
        Shares = findViewById(R.id.Shares)
        btnCreate = findViewById(R.id.btnCreate)


        val doofus: TextView = findViewById(R.id.disp)
        val message = intent.getStringExtra("grpid")
        //doofus.text = message

        val r1: EditText = findViewById(R.id.Shares)
        val r2: EditText = findViewById(R.id.Amount)
        // Assigning id of RadioGroup
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener{group, checkedId ->
            if(checkedId == R.id.radioButton1){
                r1.setVisibility(View.VISIBLE)
                r2.setVisibility(View.GONE)
            }
            else
            {
                r1.setVisibility(View.GONE)
                r2.setVisibility(View.VISIBLE)
            }

        }
        dbRef = FirebaseDatabase.getInstance().getReference("$message")

        btnCreate.setOnClickListener {
            saveMemberData("$message")
        }

    }

    private fun saveMemberData(message: String) {

        var a: Float = 0f
        var b: Float = 0f
        var c: Float = 0f
        var d: Float = 0f
        //getting values
        val mem_Name = memName.text.toString()
        val mem_Expense = memExpense.text.toString()
        val mail_ID = mailID.text.toString()
        val Shares_s = Shares.text.toString()
        val Amount_s = Amount.text.toString()
        val Due: String
        val user_ID = FirebaseAuth.getInstance().currentUser?.uid



        if (mem_Name.isEmpty()) {
            memName.error = "Please enter the member name"
        }
        if (mem_Expense.isEmpty()) {
            memExpense.error = "Please enter the total expenditure"
        }
        if (mail_ID.isEmpty()) {
            memName.error = "Please enter the member's mail ID"
        }
        if (Shares_s.isEmpty() && Amount_s.isEmpty()) {
            Shares.error = "Choose the option and enter the amount/share"
        }

        if(!Shares_s.isEmpty()){
            a = Shares.text.toString().toFloat()
            b = memExpense.text.toString().toFloat()
            c = a/100
            d = c*b
            Due = d.toString()
        }
        else{
            Due = Amount.text.toString()
        }

        val memId = dbRef.push().key!!

        val member = MemberData(memId, mem_Name, mem_Expense, mail_ID, Due, user_ID, message)

        dbRef.child(memId).setValue(member)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                memName.text.clear()
                memExpense.text.clear()
                mailID.text.clear()
                Amount.text.clear()
                Shares.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}



