package com.example.expensesharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class NewMember : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_member)

        val doofus : TextView = findViewById(R.id.disp)
        val message = intent.getStringExtra("grpid")
        doofus.text = message



        //doofus.text = grpId
    }
}