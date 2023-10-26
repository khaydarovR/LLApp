package com.example.llapp.Helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.llapp.Models.UserApp
import com.example.llapp.R

class UserAppAdapter(private var newList: List<UserApp> = listOf(
	UserApp(123, "Пусто", "Пусто","Пусто","Пусто")
) as List<UserApp>)
	: RecyclerView.Adapter<UserAppAdapter.MyHolder>(){

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
		val itemView = LayoutInflater.from(parent.context)
			.inflate(R.layout.list_item, parent,false)

		return MyHolder(itemView)
	}

	fun setApps(userApps: List<UserApp>) {
		this.newList = userApps
		notifyDataSetChanged()
	}
	override fun getItemCount(): Int {
		return newList.size
	}

	override fun onBindViewHolder(holder: MyHolder, position: Int) {
		val currentItem = newList[position]
		holder.num.text = currentItem.number.toString()
		holder.problem.text = currentItem.problem.toString()
		holder.marka.text = currentItem.marka.toString()
		holder.date.text = currentItem.date.toString()
		holder.stat.text = currentItem.status.toString()
	}

	class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
		var num: TextView = itemView.findViewById(R.id.numer)
		var problem: TextView = itemView.findViewById(R.id.problem)
		var marka: TextView = itemView.findViewById(R.id.marka)
		var date: TextView = itemView.findViewById(R.id.user_date)
		var stat: TextView = itemView.findViewById(R.id.status)
	}
}