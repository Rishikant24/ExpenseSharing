package com.example.expensesharing



import android.annotation.SuppressLint
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

    @SuppressLint("MissingInflatedId")
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

        findViewById<Button>(R.id.grpadd).setOnClickListener {
            val intent = Intent(this, NewGroup::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.existingGroupsButton).setOnClickListener {
            val intent = Intent(this, Group_List::class.java)
            startActivity(intent)
        }


    }




}