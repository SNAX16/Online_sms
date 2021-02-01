package ru.startandroid.onlinesim.activity.mainActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.activity.selectionActivity.SelectionServices

class MainActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    var apiKey:String = "ApiKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()

        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)

        api_key_browser.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sms-activate.ru/ru"))
            startActivity(browserIntent)
        }

        key_enter.setOnClickListener{
            val intent = Intent(this, SelectionServices::class.java)
            startActivity(intent)

        }
    }

    override fun onStop() {
        super.onStop()
        val editor = prefs.edit()
        val text = api_key_edit.text
        editor.putString(apiKey, text.toString()).apply()
    }
}