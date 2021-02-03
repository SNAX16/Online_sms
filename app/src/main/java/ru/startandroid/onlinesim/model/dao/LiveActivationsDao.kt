package ru.startandroid.onlinesim.model.dao

import androidx.room.*
import ru.startandroid.onlinesim.model.entity.LiveActivations


@Dao
public interface LiveActivationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLiveActivations(activations: LiveActivations)

    @Update
    fun updateLiveActivations(activations: LiveActivations)

    @Delete
    fun deleteLiveActivations(activations: LiveActivations)

    @Query("SELECT * FROM LiveActivations")
    fun getAll(): List<LiveActivations>

}