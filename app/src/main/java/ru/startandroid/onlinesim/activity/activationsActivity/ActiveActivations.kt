package ru.startandroid.onlinesim.activity.activationsActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_active_activations.*
import ru.startandroid.onlinesim.R
import java.util.ArrayList


class ActiveActivations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_activations)
        val selection: ArrayList<String> = ArrayList()

        for (i in 1..30){
            selection.add("00$i")
        }

        recyclerView_a_a.layoutManager = LinearLayoutManager(this)
        recyclerView_a_a.adapter = ActivationsAdapter(selection,this)
    }

}