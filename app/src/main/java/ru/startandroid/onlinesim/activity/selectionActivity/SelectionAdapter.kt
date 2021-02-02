package ru.startandroid.onlinesim.activity.selectionActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.activity.activationsActivity.ActiveActivations
import ru.startandroid.onlinesim.data.Data

class SelectionAdapter(selection: List<Data.ServicePrices>, This:Context,spinner:Spinner): RecyclerView.Adapter<SelectionAdapter.ViewHolder>(){

    private var context=This
    private var sel = selection
    private var spineerPosition =spinner

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
        var sp:Int = 0
        spineerPosition.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sp = position
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }

        Glide
            .with(context)
            .load("https://sms-activate.ru/assets/ico/${sel[position].Id}0.png")
            .into(holder.icon)
        holder.servicesText.text = sel[position].Id ?:"Vk"
        holder.countNumberText.text = sel[position].price.toString()?:"000"
        holder.priceServicesText.text = sel[position].count.toString()+"Р"?:"000"
        holder.buyServices.setOnClickListener{
            val intent = Intent(context, ActiveActivations::class.java)
            intent.putExtra("services",sel[position].Id)
            intent.putExtra("img","https://sms-activate.ru/assets/ico/${sel[position].Id}0.png")
            intent.putExtra("idCountry",sp)
            startActivity(intent)

        }
    }

    private fun startActivity(intent: Intent) {
        context.startActivity(intent, null)
    }

}