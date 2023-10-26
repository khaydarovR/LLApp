package com.example.llapp.Models

import androidx.databinding.ObservableField

data class UserApp(
	var number: Number,
	var marka: String,
	var problem: String,
	var date: String,
	var status: String = "Ожидает"
)
