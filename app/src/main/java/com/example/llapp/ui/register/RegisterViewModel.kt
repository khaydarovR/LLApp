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



	fun onButtonClicked() {

	}
}