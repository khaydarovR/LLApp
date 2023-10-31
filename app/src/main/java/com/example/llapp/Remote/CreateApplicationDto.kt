package com.example.llapp.Remote

import kotlinx.serialization.Serializable

@Serializable
data class CreateApplicationDto(
	var number: String,
	var marka: String,
	var problem: String,
	var date: String,
)