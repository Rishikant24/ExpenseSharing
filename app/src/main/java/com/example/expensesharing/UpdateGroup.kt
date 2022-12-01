package com.example.expensesharing

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.expensesharing.databinding.ActivityUpdateGroupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateGroup : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateGroupBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener {

            val userName = binding.userName.text.toString()
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastname.text.toString()
            //val age = binding.age.text.toString()

            updateData(userName,firstName,lastName)

        }

    }

    private fun updateData(userName: String, firstName: String, lastName: String) {

        val user_ID = FirebaseAuth.getInstance().currentUser?.uid
        database = FirebaseDatabase.getInstance().getReference("$user_ID")
        var f1: String
        var f2: String

        database.child(userName).get().addOnSuccessListener {

            if (it.exists()){

                 f1 = it.child("grpId").value.toString()
                 f2 = it.child("user_ID").value.toString()

                //Toast.makeText(this,"Successfuly Read",Toast.LENGTH_SHORT).show()
                /*binding.etusername.text.clear()
                binding.tvFirstName.text = firstname.toString()
                binding.tvLastName.text = lastName.toString()
                binding.tvAge.text = age.toString()*/
                val user = mapOf<String,String>(
                    "grp_Name" to firstName,
                    "grp_Expense" to lastName,
                    "grpId" to f1,
                    "user_ID" to f2
                )
                database.child(userName).updateChildren(user).addOnSuccessListener {

                    binding.userName.text.clear()
                    binding.firstName.text.clear()
                    binding.lastname.text.clear()
                    // binding.age.text.clear()
                    Toast.makeText(this,"Successfuly Updated",Toast.LENGTH_SHORT).show()


                }.addOnFailureListener{

                    Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()

                }}

            else{

                Toast.makeText(this,"User Doesn't Exist",Toast.LENGTH_SHORT).show()


            }

        }.addOnFailureListener{

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


        }


}}