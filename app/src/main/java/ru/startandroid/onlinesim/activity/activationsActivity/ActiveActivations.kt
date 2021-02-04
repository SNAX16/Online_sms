package ru.startandroid.onlinesim.activity.activationsActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_active_activations.*
import kotlinx.android.synthetic.main.activity_active_activations.view.*
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.activity.mainActivity.MainActivity
import ru.startandroid.onlinesim.activity.selectionActivity.SelectionServices
import ru.startandroid.onlinesim.auth.User

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

        aaViewModel.liveDataLiveActivations.observe(this, androidx.lifecycle.Observer {
            recyclerView_a_a.adapter = ActivationsAdapter(it,this,aaViewModel)
        })

        reset_button.setOnClickListener {
            aaViewModel.getlive()
            aaViewModel.getStatusActivation()
            aaViewModel.getBalance()
        }


        services_button.setOnClickListener{
            val intent = Intent(this, SelectionServices::class.java)
            startActivity(intent)
        }

        id_exit.setOnClickListener{
            User.apyKey = ""
            User.isAuthorized = false
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}

