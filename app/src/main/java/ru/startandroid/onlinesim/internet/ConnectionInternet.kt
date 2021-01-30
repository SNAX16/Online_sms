package ru.startandroid.onlinesim.internet

import android.content.Context
import android.net.ConnectivityManager

class ConnectionInternet {

  var context:Context

    constructor(context: Context) {
        this.context = context
    }

    fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.activeNetworkInfo
        if (capabilities != null && capabilities.isConnectedOrConnecting())
        {
            return true
        }
        return false
    }
}