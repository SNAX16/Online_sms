package ru.startandroid.onlinesim

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()

        api_key_browser.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sms-activate.ru/ru"))
            startActivity(browserIntent)
        }

        key_enter.setOnClickListener{
            val intent = Intent(this, SelectionServices::class.java)
            startActivity(intent)

        }
    }
}