package ru.startandroid.onlinesim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SelectionAdapter(val selection: ArrayList<String>): RecyclerView.Adapter<SelectionAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val view:View = LayoutInflater.from(parent.context).inflate(R.layout.services_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() =selection.size
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val servicesText:TextView = itemView.findViewById(R.id.name_services)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.servicesText.text = selection[position]
    }

}