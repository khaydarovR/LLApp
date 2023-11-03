package com.example.llapp.ui.acc

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.llapp.Helpers.StorageHelper

class AccViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext
	private val storage = StorageHelper(context)

	val email = ObservableField<String>()
	val username = ObservableField<String>()
	val role = ObservableField<String>()
	val token = ObservableField<String>()
	val id = ObservableField<String>()

	init {
		email.set(storage.getFromSharedPreferences("email"))
		var rolestring = storage.getFromSharedPreferences("role")
		if (rolestring == "Master"){
			role.set("Работяга")
		}
		else{
			role.set(rolestring)
		}

		username.set(storage.getFromSharedPreferences("name"))
		token.set(storage.getFromSharedPreferences("jwt"))
		id.set(storage.getFromSharedPreferences("id"))
	}

	private val _eventLogout = MutableLiveData<Boolean>()
	val eventOnLogout: MutableLiveData<Boolean> get() = _eventLogout
	fun onLogout() {
		storage.saveToSharedPreferences("isAuth", "false")
		storage.saveToSharedPreferences("jwt", "")
		_eventLogout.value = true
	}
	fun onLogouted() {
		_eventLogout.value = false
	}

}