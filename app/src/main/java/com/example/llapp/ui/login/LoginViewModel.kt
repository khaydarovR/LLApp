package com.example.llapp.ui.login

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.llapp.Helpers.StorageHelper
import com.example.llapp.Remote.AppApi
import com.example.llapp.Remote.DTOS.AuthenticationResponse
import kotlinx.coroutines.runBlocking

class LoginViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext
	private val storage = StorageHelper(context)
	private val client = AppApi.create()
	private val _text = MutableLiveData<String>().apply {
		value = "This is login Fragment"
	}
	val text: LiveData<String> = _text

	val email = ObservableField<String>()
	val password = ObservableField<String>()

	fun onLogin() {
		val e = email.get().toString()
		val p = password.get().toString()
		var res: AuthenticationResponse? = null
		runBlocking {
			res = client.login(e, p)
		}

		if (res != null) {
			storage.saveToSharedPreferences("jwt", res?.token.toString())
			storage.saveToSharedPreferences("isAuth", "true")
			storage.saveToSharedPreferences("role", res?.role.toString())
			storage.saveToSharedPreferences("email", res?.email.toString())
			storage.saveToSharedPreferences("name", res?.userName.toString())
			Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show()
			onRedirectToHome()
		}
		else{
			Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
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

	private val _eventRedirectToHome = MutableLiveData<Boolean>()
	val eventOnRedirect: MutableLiveData<Boolean> get() = _eventRedirectToHome
	fun onRedirectToHome() {
		_eventRedirectToHome.value = true
	}
	fun onRedirectedToHome() {
		_eventRedirectToHome.value = false
	}
}