package com.example.api1.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.api1.R
import com.example.api1.model.Pollution

class PollutionAdapter(var data:LiveData<Pollution>):RecyclerView.Adapter<PollutionAdapter.Holder> (){
    lateinit var context: Context

    class Holder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.list_row,parent,false) as View

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val textView1=holder.itemView.findViewById<TextView>(R.id.textViewData)
        textView1.text = data.value?.values?.get(position)?.date?:""
        val textView2=holder.itemView.findViewById<TextView>(R.id.textViewPollutionValue)
        textView2.text = data.value?.values?.get(position)?.date?:""
    }

    override fun getItemCount(): Int {
        return data.value?.values?.size?:0
    }


}