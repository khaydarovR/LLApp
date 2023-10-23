package com.example.llapp.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

	private val _text = MutableLiveData<String>().apply {
		value = "This is login Fragment"
	}
	val text: LiveData<String> = _text

	val email = ObservableField<String>()
	val password = ObservableField<String>()

	fun onButtonClicked() {
		Log.i("login VM", email.get()+ " " + password.get())
	}
}