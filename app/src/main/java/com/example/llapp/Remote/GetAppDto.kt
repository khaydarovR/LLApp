package com.example.llapp.Remote

import kotlinx.serialization.Serializable

@Serializable
data class GetAppDto(
	val userId: Int,
	val status: Int,
	val problem: String,
	val dateToArrive: String
)