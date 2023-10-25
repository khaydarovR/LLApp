package com.example.llapp.Helpers

import android.widget.DatePicker
import androidx.databinding.BindingAdapter
import com.example.llapp.ui.home.HomeViewModel
import java.util.Calendar

class DatePickerBindingAdapter {
	companion object {
		@JvmStatic
		@BindingAdapter("android:setDate")
		fun setDatePickerListener(datePicker: DatePicker, vm: HomeViewModel) {
			val calendar = Calendar.getInstance()

			datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)) { _, year, month, dayOfMonth ->
				vm.setDate(year, month + 1, dayOfMonth)
			}
		}
	}
}