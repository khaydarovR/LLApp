package com.example.llapp.Helpers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.llapp.R
import com.example.llapp.Remote.DTOS.ApplicationResponse
import java.text.ParseException
import java.text.SimpleDateFormat

class MasAppAdapter(private var newList: List<ApplicationResponse> = listOf())
	: RecyclerView.Adapter<MasAppAdapter.MyHolderForMas>(){

	private var onItemClickListener: OnItemClickListener? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderForMas {
		val itemView = LayoutInflater.from(parent.context)
			.inflate(R.layout.app_item, parent,false)

		return MyHolderForMas(itemView)
	}

	fun setOnItemClickListener(listener: OnItemClickListener) {
		onItemClickListener = listener
	}
	fun setApps(userApps: List<ApplicationResponse>) {
		this.newList = userApps
		notifyDataSetChanged()
	}
	override fun getItemCount(): Int {
		return newList.size
	}

	override fun onBindViewHolder(holder: MyHolderForMas, position: Int) {
		val currentItem = newList[position]
		holder.problem.text = currentItem.problemDescription.toString()
		holder.marka.text = currentItem.carBrand.toString() + ":" + currentItem.carNumber
		holder.arriveDate.text = "Приезд: " + convertDateFormat(currentItem.timeOfArrival.toString(), "dd.MM")
		holder.stat.text = currentItem.userName
		holder.master.text = getMasterString(currentItem)
		var resultStr = buildString {
			append(convertDateFormat(currentItem.timeOfAcceptance.toString(), "dd.MM HH:mm"))
			append(" ➜ ")
			append(convertDateFormat(currentItem.closedAt.toString(), "dd.MM HH:mm"))
		}
		holder.startEndTime.text = resultStr
		if (currentItem.currentStatus == "Waiting"){
			holder.buttonget.text = "Взять"
		}
		else if (currentItem.currentStatus == "InWork"){
			holder.buttonget.text = "Закрыть"
		}
		else {
			holder.buttonget.isVisible = false
		}


		holder.buttonget.setOnClickListener {
			val position = holder.adapterPosition
			onItemClickListener?.onButtonClick(position)

		}
	}

	private fun getMasterString(currentItem: ApplicationResponse): String {
		if (currentItem.masterId.isNullOrEmpty() == false){
			return currentItem.masterName.toString()
		}
		return "Ожидание"
	}

	class MyHolderForMas(itemView: View): RecyclerView.ViewHolder(itemView){
		var problem: TextView = itemView.findViewById(R.id.problem)
		var marka: TextView = itemView.findViewById(R.id.marka)
		var arriveDate: TextView = itemView.findViewById(R.id.user_date)
		var stat: TextView = itemView.findViewById(R.id.status)
		var master: TextView = itemView.findViewById(R.id.master)
		var startEndTime: TextView = itemView.findViewById(R.id.startEndTime)
		var buttonget: Button = itemView.findViewById(R.id.get_app_btn)
	}

	@SuppressLint("SimpleDateFormat")
	fun convertDateFormat(inputDate: String, patern: String): String {
		val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
		val outputFormat = SimpleDateFormat(patern)
		try {
			val date = inputFormat.parse(inputDate)
			return outputFormat.format(date)
		} catch (e: ParseException) {
			// Handle parsing error, such as logging or returning a default value
			e.printStackTrace()
			return ""
		}
	}
}

interface OnItemClickListener {
	fun onButtonClick(position: Int)
}