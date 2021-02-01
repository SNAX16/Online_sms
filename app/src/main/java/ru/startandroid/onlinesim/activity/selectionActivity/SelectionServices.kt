package ru.startandroid.onlinesim.activity.selectionActivity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_selection_services.*
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.activity.activationsActivity.ActiveActivations
import java.util.*


class SelectionServices : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_services)

        val selection:ArrayList<String> = ArrayList()
        val selection1:ArrayList<String> = ArrayList()

        for (i in 1..100){
            selection.add("Вконтакте$i")
        }
        for (i in 1..30){
            selection1.add("Вконтакте$i")
        }

        recyclerView_s_s.layoutManager = LinearLayoutManager(this)
        recyclerView_s_s.adapter = SelectionAdapter(selection,this)

        val adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,selection1)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_s_s.adapter = adapter

    }
}