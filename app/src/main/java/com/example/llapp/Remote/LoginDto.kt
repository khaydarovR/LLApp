package com.example.llapp.Remote

import android.provider.ContactsContract.CommonDataKinds.Email
import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
	val email: String,
	val pw: String,
)