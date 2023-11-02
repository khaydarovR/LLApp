package com.example.llapp.ui.home

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.example.llapp.Helpers.StorageHelper
import com.example.llapp.Remote.AppApi
import com.example.llapp.Remote.DTOS.ApplicationDto
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

class HomeViewModel(application: Application) : AndroidViewModel(application) {
	private val context = getApplication<Application>().applicationContext
	private val storage = StorageHelper(context)
	private lateinit var client: AppApi

	private var formattedDate = ""

	val number = ObservableField<String>()
	val marka = ObservableField<String>()
	val probleb = ObservableField<String>()

	init {
		val jwt = storage.getFromSharedPreferences("jwt")
		client = AppApi.createAuth(jwt.toString())
	}
	fun onButtonClicked(){

		var dto = ApplicationDto(
			number.get().toString(),
			marka.get().toString(),
			formattedDate,
			probleb.get().toString(),
		)
		if (dto.carNumber?.length!! > 3){
			Toast.makeText(context , "Только цифры!" , Toast.LENGTH_SHORT).show()
			return
		}
		var response: Boolean
		runBlocking {
			response = client.createApplication(dto)
		}

		if (response){
			Toast.makeText(context , "Заявка отправлена" , Toast.LENGTH_SHORT).show()
		}
		else{
			Toast.makeText(context , "Ошибка" , Toast.LENGTH_SHORT).show()
		}

	}


	fun setDate(year: Int, month: Int, dayOfMonth: Int) {
		val calendar = Calendar.getInstance()
		calendar.set(year, month, dayOfMonth)

		val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
		sdf.timeZone = TimeZone.getTimeZone("UTC")
		formattedDate = sdf.format(calendar.time)

		val timeOfArrival = "timeOfArrival: $formattedDate"
		Log.i("HOME", timeOfArrival)

	}
}