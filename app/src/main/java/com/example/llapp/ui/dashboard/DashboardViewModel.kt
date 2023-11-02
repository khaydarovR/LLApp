package com.example.llapp.ui.dashboard

import android.app.Application
import android.provider.SyncStateContract.Helpers
import androidx.datastore.preferences.protobuf.Api
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.llapp.Helpers.StorageHelper
import com.example.llapp.Models.UserApp
import com.example.llapp.Remote.AppApi
import com.example.llapp.Remote.DTOS.ApplicationResponse
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext
	private val storage = StorageHelper(context)
	private lateinit var client: AppApi

	private val _userApplications = MutableLiveData<ArrayList<ApplicationResponse>>()

	val userApplications: LiveData<ArrayList<ApplicationResponse>>
		get() = _userApplications

	init {
		val jwt = storage.getFromSharedPreferences("jwt")
		client = AppApi.createAuth(jwt.toString())
		viewModelScope.launch {
			_userApplications.value = loadFromServ()
		}
	}

	private suspend fun loadFromServ(): ArrayList<ApplicationResponse> {
		var result = client.getUserApplications()
		if (result != null){
			return result
		}
		return arrayListOf(ApplicationResponse(
			id = "2b27b14d-4fc7-4d3a-871e-7bc81132c7c6",
			carNumber = "string",
			carBrand = "string",
			timeOfArrival = "2023-11-02T19:12:44.392",
			createdAt = "2023-11-02T19:13:41.78697",
			timeOfAcceptance = null,
			closedAt = null,
			userId = "22d69616-4a10-45dd-b81a-d616f0ee6eec",
			masterId = null,
			problemDescription = "Ошибка загрузки",
			currentStatus = "Waiting"
		))
	}
}