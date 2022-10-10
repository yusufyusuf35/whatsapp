package com.deliysuf.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.deliysuf.myapplication.R
import com.deliysuf.myapplication.View.NameFragmentDirections
import com.deliysuf.myapplication.databinding.NameRowBinding
import com.deliysuf.myapplication.model.user

class nameAdapter(private val NameList:ArrayList<user>): RecyclerView.Adapter<nameAdapter.NameHolder>() {
    class NameHolder(view:View):RecyclerView.ViewHolder(view){
        var nameBinding:NameRowBinding?=null
          var binding:NameRowBinding
        init {
             binding=NameRowBinding.bind(view)
            nameBinding=binding
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameHolder {
     val view=LayoutInflater.from(parent.context).inflate(R.layout.name_row,parent,false)

     return NameHolder(view)
    }

    override fun onBindViewHolder(holder: NameHolder, position: Int) {
      holder.binding!!.nameRowText.text=NameList[position].name.toString()

        holder.itemView.setOnClickListener {
            val textView=it.findViewById<TextView>(R.id.nameRowText)
           val bundle= bundleOf("name" to textView.text.toString())

            Navigation.findNavController(it).navigate(R.id.action_nameFragment_to_massageFragment2,bundle)
      }
    }

    override fun getItemCount(): Int {
        return NameList.size
    }
}