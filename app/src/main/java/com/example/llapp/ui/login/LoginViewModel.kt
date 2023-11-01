package com.example.llapp.ui.login

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.llapp.Remote.AppApi
import com.example.llapp.Remote.DTOS.AuthenticationResponse
import kotlinx.coroutines.runBlocking

class LoginViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext
	private val client = AppApi.create()
	private val _text = MutableLiveData<String>().apply {
		value = "This is login Fragment"
	}
	val text: LiveData<String> = _text

	// your desired key
	private var jwtval = "null jwt"

	// function to save to shared preferences
	fun saveToSharedPreferences(key: String, value: String) {
		val sharedPref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
		val editor = sharedPref.edit()
		editor.putString(key, value)
		editor.apply()
	}

	val email = ObservableField<String>()
	val password = ObservableField<String>()

	fun onLogin() {
		val e = email.get().toString()
		val p = password.get().toString()
		var res: AuthenticationResponse? = null
		runBlocking {
			res = client.login(e, p)
		}
		Log.i("login", "on login")
		if (res != null) {
			saveToSharedPreferences("jwt", res?.token.toString())
			saveToSharedPreferences("isAuth", "true")
		}
	}

	private val _eventOpenRegister = MutableLiveData<Boolean>()
	val eventOpenRegister: MutableLiveData<Boolean> get() = _eventOpenRegister
	fun onRegisterOpen() {
		_eventOpenRegister.value = true
	}
	fun onRegisterNavigated() {
		_eventOpenRegister.value = false
	}
}