package ru.startandroid.onlinesim.activity.mainActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter.*
import ru.startandroid.onlinesim.R
import ru.startandroid.onlinesim.activity.selectionActivity.SelectionServices
import ru.startandroid.onlinesim.auth.User

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreference: SharedPreferences
    lateinit var mainViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreference =  getSharedPreferences("API_KEY",Context.MODE_PRIVATE)
        val api = sharedPreference.getString("apiKey","")
        api_key_edit.setText(api)
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
                CoroutineScope(Dispatchers.IO).launch {
                    try {

                        mainViewModel.setApi(api_key_edit.text.toString())

                        launch(Dispatchers.Main) {
                            User.apyKey = api_key_edit.text.toString()
                            User.isAuthorized = true
                            val intent = Intent(this@MainActivity, SelectionServices::class.java)
                            startActivity(intent)
                            finish()
                        }
                    } catch (ex: Exception) {
                        launch(Dispatchers.Main) {
                            Toast.makeText(this@MainActivity, "неверный Api ключ", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "неверный Api ключ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStop() {
        super.onStop()

        val editor = sharedPreference.edit()
        editor.putString("apiKey","${api_key_edit.text}")
        editor.apply()
    }
}