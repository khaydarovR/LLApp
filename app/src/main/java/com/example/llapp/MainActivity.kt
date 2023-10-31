package com.example.llapp

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.llapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val bottomNav: BottomNavigationView = binding.navView
		val navController = findNavController(R.id.nav_host_fragment_activity_main)

		val appBarConfiguration = AppBarConfiguration(
			setOf(
				R.id.navigation_reg,
				R.id.navigation_login,
				R.id.navigation_home,
				R.id.navigation_dashboard,
				R.id.navigation_notifications
			)
		)

		setupActionBarWithNavController(navController, appBarConfiguration)
		bottomNav.setupWithNavController(navController)

		bottomNav.setOnItemSelectedListener { item ->
			when (item.itemId) {
				R.id.navigation_login -> {
					val isAuth = getState()
					if (isAuth == "true") {
						navController.navigate(R.id.navigation_acc)
					} else {
						navController.navigate(R.id.navigation_login)
					}
					true
				}

				R.id.navigation_home -> {
					navController.navigate(R.id.navigation_home)
					false
				}

				R.id.navigation_dashboard -> {
					val role = getRole()
					if (role == "master") {
						navController.navigate(R.id.navigation_masboard)
					} else {
						navController.navigate(R.id.navigation_dashboard)
					}
					true
				}

				R.id.navigation_notifications -> {
					navController.navigate(R.id.navigation_notifications)
					false
				}

				else -> false
			}
		}
	}

	private fun getState(): String {
		val sharedPref = this.applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
		val role = sharedPref.getString("isAuth", "false")?:""
		return role
	}

	private fun getRole(): String {
		val sharedPref = this.applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
		val role = sharedPref.getString("jwt", "")?:""
		return role
	}
}