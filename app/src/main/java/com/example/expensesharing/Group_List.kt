package com.example.expensesharing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Group_List : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<GroupData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_list)

        userRecyclerview = findViewById(R.id.userList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf<GroupData>()
        getUserData()

    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("Groups")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val user = userSnapshot.getValue(GroupData::class.java)
                        userArrayList.add(user!!)

                    }

                    var varadapter = MyAdapter(userArrayList)
                    userRecyclerview.adapter = varadapter
                    varadapter.setOnItemClickListener(object: MyAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            //Toast.makeText(this@Group_List,"Peace Out",Toast.LENGTH_SHORT).show()

                            val viewid = findViewById<TextView>(R.id.tvage)
                            val message = viewid.text
                            val intent = Intent(this@Group_List, Group_Info::class.java).also {
                                it.putExtra("grpid", message)
                                startActivity(it)
                            }
                            /*intent.putExtra("grpId", userArrayList[position].grp_Name)
                            startActivity(intent)*/
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