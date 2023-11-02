package com.example.llapp.Helpers

import android.content.Context

class StorageHelper(private val context: Context) {
	fun saveToSharedPreferences(key: String, value: String) {
		val sharedPref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
		val editor = sharedPref.edit()
		editor.putString(key, value)
		editor.apply()
	}

	fun getFromSharedPreferences(key: String): String? {
		val sharedPref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
		return sharedPref.getString(key, null)
	}
}