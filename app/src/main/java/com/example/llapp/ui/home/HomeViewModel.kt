package com.example.llapp.ui.home

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext

	fun onButtonClicked(){
		val msg = getFromSharedPreferences("jwt")
		Toast.makeText(context, msg?:"null", Toast.LENGTH_SHORT).show()
	}

	fun getFromSharedPreferences(key: String): String? {
		val sharedPref = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
		return sharedPref.getString(key, null)
	}
}