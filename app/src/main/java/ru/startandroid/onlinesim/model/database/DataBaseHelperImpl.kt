package ru.startandroid.onlinesim.model.database

import ru.startandroid.onlinesim.model.entity.LiveActivations

class DataBaseHelperImpl (private val appDatabase:AppDatabase):DataBaseHelper{
    override fun addLiveActivations(activations: LiveActivations) = appDatabase.liveActivationsDao()!!.addLiveActivations(activations)

    override fun updateLiveActivations(activations: LiveActivations) = appDatabase.liveActivationsDao()!!.updateLiveActivations(activations)

    override fun deleteLiveActivations(activations: LiveActivations) = appDatabase.liveActivationsDao()!!.deleteLiveActivations(activations)

    override fun getAll(): List<LiveActivations> = appDatabase.liveActivationsDao()!!.getAll()

}