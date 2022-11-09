package com.example.expensesharing



import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class Dashboard : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // val email = intent.getStringExtra("email")
        //val displayName = intent.getStringExtra("name")
        val intent: Intent=Intent(this, MainActivity::class.java)

        //findViewById<TextView>(R.id.textView).text = email + "\n" + displayName (textview is the id of that part)
/*        val newGroupBtn1 = findViewById<Button>(R.id.addGroupButton)
        newGroupBtn1.setOnClickListener {
            val intent1 = Intent(this, addNewGroupActivity::class.java)
            startActivity(intent1)
        }*/



        auth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.signOutBtn).setOnClickListener {
            auth.signOut()
            GoogleSignIn.getClient(this, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())
                .signOut()
            startActivity(Intent(this, MainActivity:: class.java))
        }

    }




}