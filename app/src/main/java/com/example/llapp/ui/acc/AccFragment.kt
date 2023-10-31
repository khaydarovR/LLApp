package com.example.llapp.ui.acc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.llapp.R
import com.example.llapp.databinding.FragmentAccBinding
import com.example.llapp.databinding.FragmentLoginBinding


class AccFragment : Fragment() {

	private var _binding: FragmentAccBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		val accViewModel = ViewModelProvider(this).get(AccViewModel::class.java)

		_binding = FragmentAccBinding.inflate(inflater, container, false)

		val root: View = binding.root

		// Set the ViewModel to your binding
		binding.vm = accViewModel
		binding.lifecycleOwner = this

		return root
	}


	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}