package ru.startandroid.onlinesim.activity.selectionActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.activity.activationsActivity.ActiveActivations

class SelectionAdapter(var selection: ArrayList<String> , This:Context): RecyclerView.Adapter<SelectionAdapter.ViewHolder>(){

    private var context=This
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val view:View = LayoutInflater.from(parent.context).inflate(R.layout.services_layout,parent,false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() =selection.size
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val servicesText:TextView = itemView.findViewById(R.id.name_services)
        val buyServices:ImageButton = itemView.findViewById(R.id.buy_services)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.servicesText.text = selection[position]

        holder.buyServices.setOnClickListener{
            val intent = Intent(context, ActiveActivations::class.java)
            startActivity(intent)
        }
    }

    private fun startActivity(intent: Intent) {
        context.startActivity(intent, null)
    }

}