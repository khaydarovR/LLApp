package com.example.llapp.Remote

import android.util.Log
import com.example.llapp.Helpers.Const
import com.example.llapp.Models.UserApp
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.get
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.JsonSerializer
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.URLBuilder
import io.ktor.http.cio.parseResponse
import kotlinx.serialization.serializer

class AppApi (
	private val client: HttpClient
) {
	suspend fun getApps(): ArrayList<GetAppDto> {
		var response: ArrayList<GetAppDto>
		try {
			response = client.get<ArrayList<GetAppDto>>{
				url(Const.posts)
			}
			Log.i("HTTP", response.first().title)
		}catch (e: Exception){
			response = ArrayList<GetAppDto>()
			Log.i("HTTP", e.message.toString())
		}
		return response
	}

	companion object {
		fun create(): AppApi{
			return AppApi(
				client = HttpClient(Android){
					install(JsonFeature){
						serializer = KotlinxSerializer()
					}
				}
			)
		}
	}
}