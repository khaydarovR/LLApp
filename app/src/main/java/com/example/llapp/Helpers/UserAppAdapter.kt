package com.example.llapp.Helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.llapp.Models.UserApp
import com.example.llapp.R
import com.example.llapp.Remote.DTOS.ApplicationResponse
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class UserAppAdapter(private var newList: List<ApplicationResponse> = listOf())
	: RecyclerView.Adapter<UserAppAdapter.MyHolder>(){

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
		val itemView = LayoutInflater.from(parent.context)
			.inflate(R.layout.list_item, parent,false)

		return MyHolder(itemView)
	}

	fun setApps(userApps: List<ApplicationResponse>) {
		this.newList = userApps
		notifyDataSetChanged()
	}
	override fun getItemCount(): Int {
		return newList.size
	}

	override fun onBindViewHolder(holder: MyHolder, position: Int) {
		val currentItem = newList[position]
		holder.num.text = currentItem.carNumber.toString()
		holder.problem.text = currentItem.problemDescription.toString()
		holder.marka.text = currentItem.carBrand.toString()
		holder.date.text = convertDateFormat(currentItem.createdAt.toString())
		holder.stat.text = currentItem.currentStatus.toString()
	}

	class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
		var num: TextView = itemView.findViewById(R.id.numer)
		var problem: TextView = itemView.findViewById(R.id.problem)
		var marka: TextView = itemView.findViewById(R.id.marka)
		var date: TextView = itemView.findViewById(R.id.user_date)
		var stat: TextView = itemView.findViewById(R.id.status)
	}

	fun convertDateFormat(inputDate: String): String {
		val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
		val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")

		try {
			val date = inputFormat.parse(inputDate)
			return outputFormat.format(date)
		} catch (e: ParseException) {
			// Handle parsing error, such as logging or returning a default value
			e.printStackTrace()
			return "Invalid Date"
		}
	}
}