package com.example.weatherapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {

    private var isHomeSelected = true
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        findViewById<Button>(R.id.homeBtn).setOnClickListener {
            if(isHomeSelected) return@setOnClickListener
            isHomeSelected = true
            navController.navigate(R.id.action_topCitiesFragment_to_searchFragment)
        }
        findViewById<Button>(R.id.topCitiesBtn).setOnClickListener {
            if(!isHomeSelected) return@setOnClickListener
            isHomeSelected = false
            navController.navigate(R.id.action_searchFragment_to_topCitiesFragment)
        }
    }

}