package com.example.llapp.ui.register

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.llapp.Helpers.StorageHelper
import com.example.llapp.Models.ApiResponse
import com.example.llapp.Remote.AppApi
import com.example.llapp.Remote.DTOS.AuthenticationResponse
import kotlinx.coroutines.runBlocking

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext
	private val storage = StorageHelper(context)
	private val client = AppApi.create()

	val email = ObservableField<String>()
	val password = ObservableField<String>()
	val username = ObservableField<String>()
	fun onRegister() {
		val e = email.get().toString()
		val p = password.get().toString()
		val un = username.get().toString()
		var res: ApiResponse<AuthenticationResponse>? = null
		Log.i("REG", e + p + un)
		runBlocking {
			res = client.register(e, p, un)
		}

		if (res!!.isSuccess) {
			storage.saveToSharedPreferences("jwt", res?.data!!.token.toString())
			storage.saveToSharedPreferences("isAuth", "true")
			storage.saveToSharedPreferences("role", res?.data!!.role.toString())
			storage.saveToSharedPreferences("email", res?.data!!.email.toString())
			storage.saveToSharedPreferences("name", res?.data!!.userName.toString())
			storage.saveToSharedPreferences("id", "3fa85f64-5717-4562-b3fc-2c963f66afa6")
			Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show()
			onRedirectToHome()
		}
		else{
			Toast.makeText(context, res!!.errorMessage, Toast.LENGTH_SHORT).show()
		}
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