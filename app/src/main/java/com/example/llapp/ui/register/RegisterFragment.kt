package com.example.llapp.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.llapp.R
import com.example.llapp.databinding.FragmentLoginBinding
import com.example.llapp.databinding.FragmentRegBinding
import com.example.llapp.ui.login.LoginViewModel

class RegisterFragment : Fragment() {

	private var _binding: FragmentRegBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val regViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

		_binding = FragmentRegBinding.inflate(inflater, container, false)

		val root: View = binding.root

		// Set the ViewModel to your binding
		binding.vm = regViewModel
		binding.lifecycleOwner = this

		regViewModel.eventOnRedirect.observe(viewLifecycleOwner, { shouldNavigate ->
			if (shouldNavigate) {
				findNavController().navigate(R.id.action_navigation_reg_to_navigation_home)
				regViewModel.onRedirectedToHome()
			}
		})


		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}