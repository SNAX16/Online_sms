package ru.startandroid.onlinesim.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.fragment_browser_api_key.*
import kotlinx.android.synthetic.main.fragment_valid_api_key.*
import ru.startandroid.onlinesim.MainActivity
import ru.startandroid.onlinesim.R

class BrowserApiKey : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_browser_api_key, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        web_view_browser.loadUrl("https://sms-activate.ru/ru")


        button_browser_key.setOnClickListener {
        }
    }
}