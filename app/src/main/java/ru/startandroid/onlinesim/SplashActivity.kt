package ru.startandroid.onlinesim

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import ru.startandroid.onlinesim.internet.ConnectionInternet

class SplashActivity : AppCompatActivity() {

    val cd = ConnectionInternet(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (cd.isConnected()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Проблемы с интернет соединением", Toast.LENGTH_SHORT).show()
        }


    }
}