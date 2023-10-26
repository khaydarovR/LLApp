package com.example.llapp.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.llapp.Models.UserApp

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext

	private val _userApplications = MutableLiveData<ArrayList<UserApp>>()

	val userApplications: LiveData<ArrayList<UserApp>>
		get() = _userApplications

	init {
		_userApplications.value = ArrayList(listOf(
			UserApp(123, "tayota", "not start", "01-01-2023"),
			UserApp(546, "asd", "not asd", "12-23-2023"),
			UserApp(546, "asd", "not asd", "12-23-2023"),
			UserApp(546, "asd", "not asd", "12-23-2023"),
			UserApp(546, "asd", "not asd", "12-23-2023"),
			UserApp(546, "asd", "not asd", "12-23-2023"),
			UserApp(546, "asd", "not asd", "12-23-2023"),
			// rest of your data here...
		))
	}
}