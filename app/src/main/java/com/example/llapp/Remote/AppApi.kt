package com.example.llapp.Remote

import android.util.Log
import com.example.llapp.Helpers.Const
import com.example.llapp.Remote.DTOS.AuthenticationResponse
import com.example.llapp.Remote.DTOS.LoginDto
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

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
						url(Const.BASE_URL);
						header("Authorize", jwt)
					}
					install(JsonFeature){
						serializer = KotlinxSerializer()
					}
				}, isFake = true,
			)
		}
	}

	suspend fun login(email: String, pw: String): AuthenticationResponse? {
		try {
			val loginDto = LoginDto(email, pw)
			val response = client.post<AuthenticationResponse>(Const.BASE_URL+"/api/accounts/login") {
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

	suspend fun getApps(): ArrayList<GetAppDto> {
		var response: ArrayList<GetAppDto>
		if (isFake){
			response = arrayListOf(GetAppDto(34, 12, "fake problem", "fake dateToArrive"))
			return response
		}

		try {
			response = client.get<ArrayList<GetAppDto>>{
				url("api/applications")
			}
			Log.i("HTTP", response.first().problem)
		}catch (e: Exception){
			response = ArrayList<GetAppDto>()
			Log.i("HTTP", e.message.toString())
		}
		return response
	}
}