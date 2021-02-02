package ru.startandroid.onlinesim.activity.selectionActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.activity.activationsActivity.ActiveActivations
import ru.startandroid.onlinesim.data.Data

class SelectionAdapter(var selection: List<Data.ServicePrices>, This:Context): RecyclerView.Adapter<SelectionAdapter.ViewHolder>(){

    private var context=This
    private var sel = selection

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val view:View = LayoutInflater.from(parent.context).inflate(R.layout.services_layout,parent,false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() = sel.size
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val icon:ImageView =itemView.findViewById(R.id.icon_service)
        val servicesText:TextView = itemView.findViewById(R.id.name_services)
        val countNumberText:TextView = itemView.findViewById(R.id.count_number)
        val priceServicesText:TextView = itemView.findViewById(R.id.price_services)
        val buyServices:ImageButton = itemView.findViewById(R.id.buy_services)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide
            .with(context)
            .load("https://sms-activate.ru/assets/ico/${sel[position].Id}0.png")
            .into(holder.icon)
        holder.servicesText.text = sel[position].Id ?:"Vk"
        holder.countNumberText.text = sel[position].price.toString()?:"000"
        holder.priceServicesText.text = sel[position].count.toString()+"Р"?:"000"
        holder.buyServices.setOnClickListener{
            val intent = Intent(context, ActiveActivations::class.java)
            startActivity(intent)
        }
    }

    private fun startActivity(intent: Intent) {
        context.startActivity(intent, null)
    }

}