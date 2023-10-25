package com.example.llapp.ui.home

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Date

class HomeViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext
	val number = ObservableField<String>()
	val marka = ObservableField<String>()
	val probleb = ObservableField<String>()
	val year = ObservableField<Int>()
	val month = ObservableField<Int>()
	val day = ObservableField<Int>()
	fun onButtonClicked(){
		val msg = getFromSharedPreferences("jwt")
		Toast.makeText(context, msg?:"null", Toast.LENGTH_SHORT).show()
	}

	fun getFromSharedPreferences(key: String): String? {
		val sharedPref = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
		return sharedPref.getString(key, null)
	}


	fun setDate(year: Int, month: Int, dayOfMonth: Int) {
		this.year.set(year)
		this.month.set(month)
		this.day.set(dayOfMonth)
		Log.i("HOME", "day " + day.get())
	}
}