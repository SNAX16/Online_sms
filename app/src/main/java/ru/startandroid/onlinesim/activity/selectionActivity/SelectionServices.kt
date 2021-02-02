package ru.startandroid.onlinesim.activity.selectionActivity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_selection_services.*
import ru.startandroid.onlinesim.R


class SelectionServices : AppCompatActivity() {
    lateinit var ssViewModel: SelectionServicesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_services)

        ssViewModel = ViewModelProvider(this).get(SelectionServicesViewModel::class.java)
        recyclerView_s_s.layoutManager = LinearLayoutManager(this)

        ssViewModel.liveDataCountry.observe(this, androidx.lifecycle.Observer {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, it)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_s_s.adapter = adapter

        })

        ssViewModel.liveDataPrice.observe(this, androidx.lifecycle.Observer {
            recyclerView_s_s.adapter = SelectionAdapter(it, this)
        })



    }

}