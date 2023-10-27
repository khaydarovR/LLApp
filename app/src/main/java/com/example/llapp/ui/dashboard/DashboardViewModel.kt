package com.example.llapp.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.llapp.Models.UserApp
import com.example.llapp.Remote.AppApi
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext

	private val client = AppApi.create()

	private val _userApplications = MutableLiveData<ArrayList<UserApp>>()

	val userApplications: LiveData<ArrayList<UserApp>>
		get() = _userApplications

	init {
		viewModelScope.launch {
			_userApplications.value = loadFromServ()
		}
	}

	private suspend fun loadFromServ(): ArrayList<UserApp>? {
		try {
			val response = client.getApps()
			val res = response.map {
				UserApp(
					number = it.id,
					marka = "BMW",
					problem = it.title,
					date = "01-23-23", 
					status = "Ожидает"
				)
			}

			return ArrayList(res)
		} catch (e: Exception) {
			// Handle exceptions or return null
			return null
		}
	}
}