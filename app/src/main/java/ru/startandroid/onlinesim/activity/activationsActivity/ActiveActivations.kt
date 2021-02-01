package ru.startandroid.onlinesim.activity.activationsActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_active_activations.*
import ru.startandroid.onlinesim.R

class ActiveActivations : AppCompatActivity() {
    lateinit var ssViewModel: ActiveActivationsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_activations)

        ssViewModel = ViewModelProvider(this).get(ActiveActivationsViewModel::class.java)

        recyclerView_a_a.layoutManager = LinearLayoutManager(this)
        recyclerView_a_a.adapter = ActivationsAdapter(ssViewModel.liveData.value,this)

    }

}