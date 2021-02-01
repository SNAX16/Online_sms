package ru.startandroid.onlinesim.activity.activationsActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.util.ArrayList

class ActiveActivationsViewModel(application: Application) : AndroidViewModel(application) {

    val liveData = MutableLiveData<ArrayList<String>>()

    init {
        onStart()
    }

    fun onStart() {
        val selection: ArrayList<String> = ArrayList()

        for (i in 1..30){
            selection.add("ID$i")
        }

        liveData.value = selection

    }
}