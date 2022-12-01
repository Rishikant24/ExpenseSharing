package com.example.expensesharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Half.toFloat
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

import java.net.IDN

class UpdateDues : AppCompatActivity() {

    private lateinit var TripName: EditText
    private lateinit var TripExpense: EditText
    //private lateinit var mailID: EditText
    private lateinit var Shares: EditText
    private lateinit var Amount: EditText
    private lateinit var Size: EditText
    private lateinit var database : DatabaseReference
    private lateinit var database2 : DatabaseReference
    private lateinit var userArrayList : ArrayList<MemberData>

    private lateinit var btnCreate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_dues)


        TripName = findViewById(R.id.memName)
        TripExpense = findViewById(R.id.memExpense)
        //mailID = findViewById(R.id.mailID)
        Amount = findViewById(R.id.Amount)
        Shares = findViewById(R.id.Shares)
        btnCreate = findViewById(R.id.btnCreate)
        Size = findViewById(R.id.size)
        userArrayList = arrayListOf<MemberData>()

        userArrayList.clear()
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
        database = FirebaseDatabase.getInstance().getReference("$message")

        btnCreate.setOnClickListener {
            saveMemberData("$message")
        }

    }

    private fun readData(Due: String, message: String, size: String) {

        var a: Float = 0f
        var b: Float = 0f
        var c: Float = 0f
       database = FirebaseDatabase.getInstance().getReference("$message")
        var f : Int = size.toInt()
        var g : Int = f-1
        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val user = userSnapshot.getValue(MemberData::class.java)
                        userArrayList.add(user!!)



                    }
                    for(i in 0..g){
                        var mail1 = userArrayList[i].mail_ID
                        var Due1 = userArrayList[i].Due
                        a = Due.toFloat()
                        if (Due1 != null) {
                            b = Due1.toFloat()
                        }
                        c = a + b
                        var Due2 = c.toString()
                        if (mail1 != null) {
                            FirebaseDatabase.getInstance().getReference("$message").child(mail1).child("due").setValue(Due2)
                        }
                    }



                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

    })
    }

    private fun saveMemberData(message: String) {

        var a: Float = 0f
        var b: Float = 0f
        var c: Float = 0f
        var d: Float = 0f
        var e: Int = 0
        //getting values
        val mem_Name = TripName.text.toString()
        val mem_Expense = TripExpense.text.toString()
        //val mail_ID = mailID.text.toString()
        val Shares_s = Shares.text.toString()
        val Amount_s = Amount.text.toString()
        val size = Size.text.toString()
        val Due: String
        val user_ID = FirebaseAuth.getInstance().currentUser?.uid



        if (mem_Name.isEmpty()) {
            TripName.error = "Please enter the Trip/expenditure name"
        }
        if (mem_Expense.isEmpty()) {
            TripExpense.error = "Please enter the total expenditure"
        }
        if (Shares_s.isEmpty() && Amount_s.isEmpty()) {
            Shares.error = "Choose the option and enter the amount/share"
        }

        if(Shares_s.isNotEmpty()){
            a = Shares.text.toString().toFloat()
            b = TripExpense.text.toString().toFloat()
            c = a/100
            d = c*b
            Due = d.toString()
            Toast.makeText(this, "$Due", Toast.LENGTH_LONG).show()
            readData(Due, message, size)
        }
        else{
            Due = Amount.text.toString()
            readData(Due, message, size)
        }



       /* val memId = dbRef.push().key!!

        val member = MemberData(memId, mem_Name, mem_Expense, mail_ID, Due, user_ID, message)

        dbRef.child(mail_ID).setValue(member)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                memName.text.clear()
                memExpense.text.clear()
                mailID.text.clear()
                Amount.text.clear()
                Shares.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }*/

    }

    private fun updateData(userName: String, duef: String, message: String, d: Int) {

        val user_ID = FirebaseAuth.getInstance().currentUser?.uid
        database2 = FirebaseDatabase.getInstance().getReference("$message")
        var f1: String
        var f2: String
        var f3: String
        var f4: String



        database2.child(userName).get().addOnSuccessListener {

            if (it.exists()){

                f1 = it.child("mem_Name").value.toString()
                f3 = it.child("mem_Expense").value.toString()
                f2 = it.child("user_ID").value.toString()
                f4 = it.child("memId").value.toString()

                //Toast.makeText(this,"Successfuly Read",Toast.LENGTH_SHORT).show()
                /*binding.etusername.text.clear()
                binding.tvFirstName.text = firstname.toString()
                binding.tvLastName.text = lastName.toString()
                binding.tvAge.text = age.toString()*/
                val user = mapOf<String,String>(
                    "mail_ID" to userName,
                    "memId" to f4,
                    "due" to duef,
                    "grp_ID" to message,
                    "mem_Name" to f1,
                    "mem_Expense" to f3,
                    "user_ID" to f2
                )
                database2.child(userName).updateChildren(user).addOnSuccessListener {


                    Toast.makeText(this,"Successfuly Updated $d",Toast.LENGTH_SHORT).show()


                }.addOnFailureListener{

                    Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()

                }}

            else{

                Toast.makeText(this,"User Doesn't Exist",Toast.LENGTH_SHORT).show()


            }

        }.addOnFailureListener{

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


        }


    }
}



