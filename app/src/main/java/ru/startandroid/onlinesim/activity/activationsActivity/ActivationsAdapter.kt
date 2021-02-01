package ru.startandroid.onlinesim.activity.activationsActivity

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
import java.util.ArrayList

class ActivationsAdapter(var selection: ArrayList<String>?, This:Context): RecyclerView.Adapter<ActivationsAdapter.ViewHolder>(){

    private var context=This
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val view:View = LayoutInflater.from(parent.context).inflate(R.layout.activations_layout,parent,false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() =selection!!.size
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val activationsText:TextView = itemView.findViewById(R.id.id_activation)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.activationsText.text = selection!![position]

    }

    private fun startActivity(intent: Intent) {
        context.startActivity(intent, null)
    }

}