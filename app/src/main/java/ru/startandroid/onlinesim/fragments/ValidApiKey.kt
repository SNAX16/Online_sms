package ru.startandroid.onlinesim.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_valid_api_key.*
import ru.startandroid.onlinesim.R


class ValidApiKey : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_valid_api_key, container, false)
    }

    override fun onStart() {
        super.onStart()

        button_valid_key.setOnClickListener{
            //(activity as MainActivity).navController.navigate(R.id.action_validApiKey_to_browserApiKey)
        }
    }

}