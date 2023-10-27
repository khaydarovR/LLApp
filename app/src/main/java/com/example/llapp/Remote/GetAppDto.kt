package com.example.llapp.Remote

import kotlinx.serialization.Serializable

@Serializable
data class GetAppDto(
	val userId: Int,
	val id: Int,
	val title: String,
	val body: String
)