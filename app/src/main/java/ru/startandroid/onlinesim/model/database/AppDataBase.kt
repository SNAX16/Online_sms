package ru.startandroid.onlinesim.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.startandroid.onlinesim.model.dao.LiveActivationsDao
import ru.startandroid.onlinesim.model.entity.LiveActivations

@Database(entities = [LiveActivations::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun liveActivationsDao(): LiveActivationsDao?
}

object DatabaseBuilder{
    private var INSTANCE:AppDatabase? = null

    fun getInstance(context: Context):AppDatabase{
        if (INSTANCE == null){
            synchronized(AppDatabase::class.java){
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) = Room.databaseBuilder(
        context.applicationContext,AppDatabase::class.java,"DBLiveActivations"
    ).build()

}