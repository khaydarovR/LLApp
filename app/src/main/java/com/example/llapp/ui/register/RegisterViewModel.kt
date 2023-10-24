package com.example.llapp.ui.register

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext
	private val _text = MutableLiveData<String>().apply {
		value = "This is login Fragment"
	}
	val text: LiveData<String> = _text

	// your desired key
	private var jwtval = "null jwt"

	// function to save to shared preferences
	fun saveToSharedPreferences(value: String) {
		val sharedPref = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
		val editor = sharedPref.edit()
		editor.putString("jwt", value)
		editor.apply()
	}

	val email = ObservableField<String>()
	val password = ObservableField<String>()

	fun onButtonClicked() {
		Log.i("login VM", email.get()+ " " + password.get())
		jwtval = "new jwt before auth"
		saveToSharedPreferences(jwtval)
	}
}