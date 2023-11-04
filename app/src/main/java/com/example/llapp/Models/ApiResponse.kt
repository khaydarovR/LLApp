package com.example.llapp.Models

class ApiResponse<T : Any>(
	val isSuccess: Boolean,
	var data: T? = null,
	val errorMessage: String? = null
) {
	fun map(function: (T?) -> T?) = apply {
		this.data = function.invoke(this.data)
	}
}