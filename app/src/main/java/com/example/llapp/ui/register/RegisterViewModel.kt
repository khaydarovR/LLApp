package com.example.llapp.ui.register

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext

	val email = ObservableField<String>()
	val password = ObservableField<String>()
	val username = ObservableField<String>()
	fun onRegister() {

	}
}