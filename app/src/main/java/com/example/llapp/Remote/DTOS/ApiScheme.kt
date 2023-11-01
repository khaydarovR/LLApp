package com.example.llapp.Remote.DTOS

import kotlinx.serialization.Serializable
@Serializable
data class ApplicationDto(
	val carNumber: String? = null,
	val carBrand: String? = null,
	val timeOfArrival: String? = null,
	val problemDescription: String? = null
)

@Serializable
data class ApplicationResponse(
	val id: String,
	val carNumber: String? = null,
	val carBrand: String? = null,
	val timeOfArrival: String? = null,
	val createdAt: String,
	val timeOfAcceptance: String? = null,
	val closedAt: String? = null,
	val userId: String,
	val masterId: String? = null,
	val problemDescription: String? = null,
	val currentStatus: String? = null
)

@Serializable
data class AuthenticationResponse(
	val userName: String? = null,
	val email: String? = null,
	val role: String? = null,
	val token: String? = null
)

@Serializable
data class ChangeRoleDto(
	val userId: String? = null,
	val role: String? = null
)

@Serializable
data class LoginDto(
	val email: String? = null,
	val password: String? = null
)

@Serializable
data class RegisterDto(
	val userName: String? = null,
	val email: String? = null,
	val password: String? = null
)

@Serializable
data class StatisticsResponse(
	val usersCount: Int,
	val mastersCount: Int,
	val applicationsCount: Int,
	val waitingApplicationsCount: Int,
	val inWorkApplicationsCount: Int,
	val closedApplicationsCount: Int
)