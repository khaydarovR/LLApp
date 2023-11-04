package com.example.llapp.Remote

import android.text.BoringLayout
import android.util.Log
import com.example.llapp.Helpers.Const
import com.example.llapp.Models.ApiResponse
import com.example.llapp.Remote.DTOS.ApplicationDto
import com.example.llapp.Remote.DTOS.ApplicationResponse
import com.example.llapp.Remote.DTOS.AuthenticationResponse
import com.example.llapp.Remote.DTOS.LoginDto
import com.example.llapp.Remote.DTOS.RegisterDto
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.DefaultHttpRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.json.JSONObject

class AppApi (
	private val client: HttpClient,
	private val isFake: Boolean
) {
	companion object {

		fun create(): AppApi{
			return AppApi(
				client = HttpClient(Android){
					install(JsonFeature){
						serializer = KotlinxSerializer()
					}
				}, isFake = true,
			)
		}

		/**
		 * Создать авторизованного клиента
		 */
		fun createAuth(jwt: String): AppApi{
			return AppApi(
				client = HttpClient(Android){
					install(DefaultRequest){
						header("Authorization", "Bearer " + jwt)
					}
					install(JsonFeature){
						serializer = KotlinxSerializer()
					}
				}, isFake = true,
			)
		}
	}

	suspend fun getUserApplications(): ArrayList<ApplicationResponse>? {
		try {
			val response = client.get<ArrayList<ApplicationResponse>>(Const.BASE_URL+ "api/user/applications")
			Log.i("HTTP", "Get USER applications successful " + response.first().createdAt)
			return response
		} catch (e: Exception) {
			Log.i("HTTP", e.message.toString())
		}
		return null
	}

	suspend fun getMasterApplications(status: String): ArrayList<ApplicationResponse>? {
		try {
			val response = client.get<ArrayList<ApplicationResponse>>(Const.BASE_URL+ "api/master/applications/$status")
			Log.i("HTTP", "Get MASTER applications successful " + response.count())
			return response
		} catch (e: Exception) {
			Log.i("HTTP", e.message.toString())
		}
		return null
	}

	suspend fun getForWork(applicationId: String, action: String): ApplicationResponse? {
		try {
			val response = client.post<ApplicationResponse>(Const.BASE_URL+"api/master/applications/$action/$applicationId" ) {
				contentType(ContentType.Application.Json)
			}
			Log.i("HTTP", "Take application successful " + response.masterId)
			return response
		} catch (e: Exception) {
			Log.i("HTTP", e.message.toString())
		}
		return null
	}

	suspend fun login(email: String, pw: String): AuthenticationResponse? {
		try {
			val loginDto = LoginDto(email, pw)
			val response = client.post<AuthenticationResponse>(Const.BASE_URL+"api/accounts/login") {
				contentType(ContentType.Application.Json)
				body = loginDto
			}
			Log.i("HTTP", "Login successful " + response.token)
			return response
		} catch (e: Exception) {
			Log.i("HTTP", e.message.toString())
		}
		return null
	}

	suspend fun register(email: String, pw: String, username: String): ApiResponse<AuthenticationResponse>? {
		try {
			val registerDto = RegisterDto(username, email, pw)
			val response = client.post<AuthenticationResponse>(Const.BASE_URL+"api/accounts/register") {
				contentType(ContentType.Application.Json)
				body = registerDto
			}
			Log.i("HTTP", "Login successful " + response.token)
			return ApiResponse(true, response, null)
		} catch (e: Exception) {
			Log.i("HTTP", e.message.toString())
			return ApiResponse(false, null, parseErrorMessage(e.message.toString()))
		}
	}
	fun parseErrorMessage(response: String): String {
		val errorText = response.substring(response.indexOf("error") + 5).trim()
		return errorText
	}

	suspend fun createApplication(createApplicationDto: ApplicationDto): Boolean {
		try {
			val response = client.post<ApplicationResponse>(Const.BASE_URL+"api/user/applications") {
				contentType(ContentType.Application.Json)
				body = createApplicationDto
			}
			Log.i("HTTP", "Create app successful " + response.createdAt)
			return true
		} catch (e: Exception) {
			Log.i("HTTP", e.message.toString())
		}
		return false
	}
}