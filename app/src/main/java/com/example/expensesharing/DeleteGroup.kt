package com.example.expensesharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.expensesharing.databinding.ActivityDeleteGroupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteGroup : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteGroupBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener {

            val userName = binding.userName.text.toString()
            /*val firstName = binding.firstName.text.toString()
            val lastName = binding.lastname.text.toString()
            val age = binding.age.text.toString()*/

            if(userName.isNotEmpty())
                updateData(userName)
            else
                Toast.makeText(this,"Please Enter the Group Name",Toast.LENGTH_SHORT).show()

        }

    }

    private fun updateData(userName: String) {

        val user_ID = FirebaseAuth.getInstance().currentUser?.uid
        database = FirebaseDatabase.getInstance().getReference("$user_ID")

        database.child(userName).removeValue().addOnSuccessListener {

            binding.userName.text.clear()
            Toast.makeText(this,"Successfully Deleted",Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Delete",Toast.LENGTH_SHORT).show()

        }}
}