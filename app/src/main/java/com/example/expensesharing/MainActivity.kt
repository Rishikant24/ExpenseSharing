package com.example.expensesharing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth  = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        supportActionBar?.hide()
        Handler().postDelayed(
            {
                if(user!=null) {

                    val intent = Intent(this, Dashboard::class.java)
                    startActivity(intent)
                }
                else
                {
                    val intent = Intent(this, LoginPage::class.java)
                    startActivity(intent)
                }
            },
            3000
        )
    }
}