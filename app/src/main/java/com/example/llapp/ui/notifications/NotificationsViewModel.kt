package com.example.llapp.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

	private val _text = MutableLiveData<String>().apply {
		value = "Уведомлений пока нет"
	}
	val text: LiveData<String> = _text
}