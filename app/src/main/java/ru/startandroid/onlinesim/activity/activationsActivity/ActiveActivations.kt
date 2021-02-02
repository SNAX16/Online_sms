package ru.startandroid.onlinesim.activity.activationsActivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_active_activations.*
import kotlinx.android.synthetic.main.activity_active_activations.view.*
import ru.startandroid.onlinesim.R

class ActiveActivations : AppCompatActivity() {
    lateinit var aaViewModel: ActiveActivationsViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_activations)

        aaViewModel = ViewModelProvider(this).get(ActiveActivationsViewModel::class.java)


        aaViewModel.liveDataBalance.observe(this, androidx.lifecycle.Observer {
            toolbar.id_balance.text = it.balance.toString()
        })


        recyclerView_a_a.layoutManager = LinearLayoutManager(this)

        aaViewModel.liveDataBalance.observe(this, androidx.lifecycle.Observer {
            recyclerView_a_a.adapter = ActivationsAdapter(aaViewModel.liveData.value!!,this)
        })

    }

}