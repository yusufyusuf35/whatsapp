package com.deliysuf.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deliysuf.myapplication.R
import com.deliysuf.myapplication.databinding.MassageRowBinding
import com.deliysuf.myapplication.model.massageModel

class MassageAdapter(private val MassageList:ArrayList<massageModel>,
                     private val senderId:String,
                     private val receiverId:String ): RecyclerView.Adapter<MassageAdapter.MassageHolder>() {

    class MassageHolder(view: View):RecyclerView.ViewHolder(view){
        var sendertextView:TextView
        var receiverTextView:TextView
        init {
             sendertextView=view.findViewById(R.id.senderText)
             receiverTextView=view.findViewById(R.id.receiverText)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MassageHolder {
        val view=LayoutInflater.from(parent.context).
        inflate(R.layout.massage_row,parent,false)
        return MassageHolder(view)
    }

    override fun onBindViewHolder(holder: MassageHolder, position: Int) {
        if(MassageList[position].uid.equals(receiverId)&&!MassageList[position].userMassages.equals("")){
            holder.receiverTextView.visibility=View.VISIBLE
            holder.receiverTextView.text=MassageList[position].userMassages
            holder.sendertextView.visibility=View.INVISIBLE
        }else if(MassageList[position].uid.equals(senderId)&&!MassageList[position].userMassages.equals("")){
            holder.sendertextView.visibility=View.VISIBLE
            holder.sendertextView.text=MassageList[position].userMassages
            holder.receiverTextView.visibility=View.INVISIBLE

        }    }

    override fun getItemCount(): Int {
        return MassageList.size
    }


}