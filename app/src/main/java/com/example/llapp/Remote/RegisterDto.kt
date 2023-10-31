package com.example.llapp.Remote

import android.provider.ContactsContract.CommonDataKinds.Email
import kotlinx.serialization.Serializable

@Serializable
data class RegisterDto(
	val email: String,
	val pw: String,
	val userName: String
)