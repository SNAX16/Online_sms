package ru.startandroid.onlinesim.activity.selectionActivity

import android.os.AsyncTask
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_selection_services.*
import org.jetbrains.annotations.NotNull
import ru.sms_activate.SMSActivateApi
import ru.sms_activate.response.api_activation.SMSActivateGetCountriesResponse
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.activity.data.GetCountry
import ru.startandroid.onlinesim.auth.User
import java.math.BigDecimal
import java.util.*


class SelectionServices : AppCompatActivity() {
    lateinit var ssViewModel: SelectionServicesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_services)

        ssViewModel = ViewModelProvider(this).get(SelectionServicesViewModel::class.java)

        val text: ArrayList<String>? = ssViewModel.liveData1.value
        val text2: List<User.Info>? = ssViewModel.liveData2.value


        recyclerView_s_s.layoutManager = LinearLayoutManager(this)
        if (text2 != null) {
            recyclerView_s_s.adapter = SelectionAdapter(text2, this)
        }

        if (text != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, text)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_s_s.adapter = adapter
        }
    }


    }