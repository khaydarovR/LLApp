package com.example.llapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.llapp.databinding.FragmentHomeBinding
import com.example.llapp.databinding.FragmentLoginBinding
import com.example.llapp.ui.login.LoginViewModel
import java.util.Calendar

class HomeFragment : Fragment() {

	private var _binding: FragmentHomeBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

		_binding = FragmentHomeBinding.inflate(inflater, container, false)

		val root: View = binding.root


		// Set the ViewModel to your binding
		binding.vm = homeViewModel
		binding.lifecycleOwner = this

		return root
	}

	@BindingAdapter("android:onDateChanged")
	fun setDatePickerListener(datePicker: DatePicker, viewModel: HomeViewModel) {
		val calendar = Calendar.getInstance()

		datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)) { view, year, monthOfYear, dayOfMonth ->
			viewModel.setDate(year, monthOfYear + 1, dayOfMonth)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}