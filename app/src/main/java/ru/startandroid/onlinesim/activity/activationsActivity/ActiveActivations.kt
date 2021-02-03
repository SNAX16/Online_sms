package ru.startandroid.onlinesim.activity.activationsActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_active_activations.*
import kotlinx.android.synthetic.main.activity_active_activations.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.model.database.DataBaseHelperImpl
import ru.startandroid.onlinesim.model.database.DatabaseBuilder
import java.util.function.Consumer

class ActiveActivations : AppCompatActivity() {
    lateinit var aaViewModel: ActiveActivationsViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_activations)
        aaViewModel = ViewModelProvider(this).get(ActiveActivationsViewModel::class.java)
        recyclerView_a_a.layoutManager = LinearLayoutManager(this)
        aaViewModel.getlive()


        aaViewModel.liveDataBalance.observe(this, androidx.lifecycle.Observer {
            toolbar.id_balance.text = it.balance.toString()
        })

        aaViewModel.liveDatainter.observe(this, androidx.lifecycle.Observer {
            recyclerView_a_a.adapter = ActivationsAdapter(it,this)

        })


    }

    override fun onStart() {
        super.onStart()
        aaViewModel.getlive()
    }
}
