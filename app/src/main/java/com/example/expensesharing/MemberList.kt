package com.example.expensesharing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class MemberList : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<MemberData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_list)

        val message = intent.getStringExtra("grpid")
        userRecyclerview = findViewById(R.id.userList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf<MemberData>()
        //val texty: TextView = findViewById(R.id.textView2)
        //texty.text = message

        getUserData("$message")

    }

    private fun getUserData(message: String) {

        dbref = FirebaseDatabase.getInstance().getReference("$message")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val user = userSnapshot.getValue(MemberData::class.java)
                        userArrayList.add(user!!)

                    }

                    var varadapter = MyAdapter2(userArrayList)
                    userRecyclerview.adapter = varadapter
                    varadapter.setOnItemClickListener(object: MyAdapter2.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            //Toast.makeText(this@Group_List,"Peace Out",Toast.LENGTH_SHORT).show()

                            //val viewid = findViewById<TextView>(R.id.tvage)
                            //var message = viewid.text.toString()
                          //  val intent = Intent(this@Group_List, Group_Info::class.java)*//*.also {
                            //    it.putExtra("grpid", message)
                              //  startActivity(it)
                            //}
                            //intent.putExtra("grpId", userArrayList[position].grpId)
                            //startActivity(intent)
                        }
                    })


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}