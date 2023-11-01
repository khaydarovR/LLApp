package com.example.llapp.ui.acc

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.databinding.ObservableField
import androidx.datastore.core.DataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.prefs.Preferences

class AccViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext
	private val _text = MutableLiveData<String>().apply {
		value = "This is acc Fragment"
	}
	val text: LiveData<String> = _text

	
	private var jwtval = "null jwt"

	// function to save to shared preferences
	fun saveToSharedPreferences(key: String, value: String) {
		val sharedPref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
		val editor = sharedPref.edit()
		editor.putString(key, value)
		editor.apply()
	}

	fun Logout() {
		saveToSharedPreferences("isAuth", "false")
		saveToSharedPreferences("jwt", "")
	}

}