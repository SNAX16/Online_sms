package ru.startandroid.onlinesim.activity.mainActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter.*
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.activity.selectionActivity.SelectionServices
import ru.startandroid.onlinesim.auth.User

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        mainViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        api_key_browser.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sms-activate.ru/ru"))
            startActivity(browserIntent)
        }

        key_enter.setOnClickListener {
            if (api_key_edit.text.length == 32) {
                User.apyKey = api_key_edit.text.toString()
                User.isAuthorized = true

                 val intent = Intent(this, SelectionServices::class.java)
                 startActivity(intent)
                 finish()

            } else {
                Toast.makeText(this, "неверный Api ключ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStop() {
        super.onStop()
    }
}