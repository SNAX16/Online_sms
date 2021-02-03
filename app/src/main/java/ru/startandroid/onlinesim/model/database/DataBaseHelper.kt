package ru.startandroid.onlinesim.model.database

import ru.startandroid.onlinesim.model.entity.LiveActivations

interface DataBaseHelper {
    fun addLiveActivations(activations: LiveActivations)

    fun updateLiveActivations(activations: LiveActivations)

    fun deleteLiveActivations(activations: LiveActivations)

    fun getAll(): List<LiveActivations>
}