package com.example.llapp.ui.masboard

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.llapp.Helpers.Const
import com.example.llapp.Helpers.StorageHelper
import com.example.llapp.Remote.AppApi
import com.example.llapp.Remote.DTOS.ApplicationResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MasboardViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext
	private lateinit var client: AppApi
	private var action: String = "accept"
	private val storage = StorageHelper(context)

	private val _masterApplications = MutableLiveData<ArrayList<ApplicationResponse>>()
	val itemClickedEvent = MutableLiveData<Int>()
	val masApplications: LiveData<ArrayList<ApplicationResponse>>
		get() = _masterApplications


	init {
		var jwt = storage.getFromSharedPreferences("jwt")?:""
		client = AppApi.createAuth(jwt)

		viewModelScope.launch {
			_masterApplications.value = loadFromServ(Const.Waiting)
		}
	}

	fun onButtonClick(position: Int) {
		itemClickedEvent.postValue(position)
		Log.i("BTN", "clicked $position")
		var appItem = masApplications.value!!.get(position)
		val res = runBlocking { client.getForWork(appItem.id, action) }
		if (res != null){
			if (action == "accept"){
				Toast.makeText(context, "Взята на работу", Toast.LENGTH_SHORT).show()
			}
			else if (action == "close"){
				Toast.makeText(context, "Заявка закрыта", Toast.LENGTH_SHORT).show()
			}
		}
	}

	fun OnSelectStatus(status: String){
		Log.i("BTN", status)
		if (status  == Const.InWork){
			this.action = "close"
		}
		else if (status == Const.Waiting){
			this.action = "accept"
		}
		_masterApplications.value = runBlocking { loadFromServ(status) }
	}

	private suspend fun loadFromServ(status: String): ArrayList<ApplicationResponse> {
		var result = client.getMasterApplications(status)
		if (result != null){
			if (result.count() == 0){
				Toast.makeText(context, "Пусто", Toast.LENGTH_SHORT).show()
			}
			return result
		}

		return arrayListOf(ApplicationResponse(
			id = "2b27b14d-4fc7-4d3a-871e-7bc81132c7c6",
			carNumber = "123",
			carBrand = "string",
			timeOfArrival = "2023-11-02T19:12:44.392",
			createdAt = "2023-11-02T19:13:41.78697",
			timeOfAcceptance = "",
			closedAt = "",
			userId = "22d69616-4a10-45dd-b81a-d616f0ee6eec",
			masterId = "",
			problemDescription = "Ошибка загрузки",
			currentStatus = "Waiting"
		))
	}
}