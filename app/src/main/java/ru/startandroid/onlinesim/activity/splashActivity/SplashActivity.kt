package ru.startandroid.onlinesim.activity.splashActivity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import ru.startandroid.onlinesim.activity.mainActivity.MainActivity
import ru.startandroid.onlinesim.auth.User
import ru.startandroid.onlinesim.internet.ConnectionInternet

class SplashActivity : AppCompatActivity() {

    val cd = ConnectionInternet(this)
    private lateinit var prefs: SharedPreferences
    var apiKey:String = "ApiKey"

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