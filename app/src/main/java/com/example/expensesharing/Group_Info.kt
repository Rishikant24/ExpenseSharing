package com.example.expensesharing

import android.content.Intent
import android.os.Build.VERSION_CODES.M
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import androidx.fragment.app.Fragment
import com.example.expensesharing.databinding.ActivityMainBinding



class Group_Info : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_info)

        val bundle : Bundle?= intent.extras
        val grpId = bundle!!.getString("grpId")

        //val bundle: Bundle? = intent.extras
        //val message: String? = intent.getString("grpId")

        var message = grpId

        val frag1: Button = findViewById(R.id.fragment1Btn)
        val frag2: Button = findViewById(R.id.fragment2Btn)
        //frag1.text = message

        frag2.setOnClickListener()
        {

            val intent = Intent(this, NewMember::class.java).also {
                it.putExtra("grpid", message)
                startActivity(it)
            }
            //intent.putExtra("grpId", grpId)
            //startActivity(intent)
        }

    }


}