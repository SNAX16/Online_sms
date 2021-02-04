package ru.startandroid.onlinesim.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LiveActivations(@PrimaryKey(autoGenerate = true)
                                       @ColumnInfo(name = "liveActivations_id") val liveActivations_id: Int,
                           @ColumnInfo(name = "service") val service: String,
                           @ColumnInfo(name = "idCountry") val idCountry: Int,
                           @ColumnInfo(name = "phoneNumber") val phoneNumber: Long?,
                           @ColumnInfo(name = "kodSms") val kodSms: String?=null)