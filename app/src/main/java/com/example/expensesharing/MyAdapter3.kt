package com.example.expensesharing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter3(private val userList : ArrayList<TripData>) : RecyclerView.Adapter<MyAdapter3.MyViewHolder>() {

    private lateinit var mListener2 : onItemClickListener
    lateinit var payy : String
    lateinit var sum : String

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener)
    {
        mListener2 = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_trip_item,
            parent,false)
        return MyViewHolder(itemView, mListener2)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.firstName.text = currentitem.Trip_Name
        holder.lastName.text = currentitem.Size
        holder.age.text = currentitem.Trip_Expense
        payy = currentitem.Payer.toString()
        sum = currentitem.sum.toString()

        holder.payer.text = "Each member of the Group owes $payy : $sum rupees"

    }

    override fun getItemCount(): Int {

        return userList.size
    }


    class MyViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val firstName : TextView = itemView.findViewById(R.id.tvfirstName)
        val lastName : TextView = itemView.findViewById(R.id.tvlastName)
        val age : TextView = itemView.findViewById(R.id.tvmail)
        val payer : TextView = itemView.findViewById(R.id.tvpayer)


        init{
            itemView.setOnClickListener(){
                listener.onItemClick(adapterPosition)
            }
        }

    }

}