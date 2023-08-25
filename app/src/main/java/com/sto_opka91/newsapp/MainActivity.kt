package com.sto_opka91.newsapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)
        Handler(Looper.myLooper()!!).postDelayed({
            setContentView(R.layout.activity_main)
            // Найти NavHostFragment
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            // Получить NavController
            navController = navHostFragment.navController
            // Настроить BottomNavigationView с NavController
            val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav_menu)
            NavigationUI.setupWithNavController(bottomNavView, navController)
        }, 5000)

    }


}