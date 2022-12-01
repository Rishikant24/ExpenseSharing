package com.example.expensesharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.expensesharing.databinding.ActivityUpdateGroupBinding
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
            //val firstName = binding.firstName.text.toString()
            val lastName = binding.lastname.text.toString()
            //val age = binding.age.text.toString()

            updateData(userName,lastName)

        }

    }

    private fun updateData(userName: String, lastName: String) {

        database = FirebaseDatabase.getInstance().getReference("Groups")
        val user = mapOf<String,String>(
            "grp_Name" to userName,
            "grp_Expense" to lastName
        )

        database.child(userName).updateChildren(user).addOnSuccessListener {

            binding.userName.text.clear()
           // binding.firstName.text.clear()
            binding.lastname.text.clear()
           // binding.age.text.clear()
            Toast.makeText(this,"Successfuly Updated",Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()

        }}
}