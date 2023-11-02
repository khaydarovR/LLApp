package com.example.llapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.llapp.R
import com.example.llapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

	private var _binding: FragmentLoginBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

		_binding = FragmentLoginBinding.inflate(inflater, container, false)

		val root: View = binding.root

		// Set the ViewModel to your binding
		binding.vm = loginViewModel
		binding.lifecycleOwner = this

		loginViewModel.eventOpenRegister.observe(viewLifecycleOwner, { shouldNavigate ->
			if (shouldNavigate) {
				findNavController().navigate(R.id.action_navigation_login_to_navigation_reg)
				loginViewModel.onRegisterNavigated()
			}
		})

		loginViewModel.eventOnRedirect.observe(viewLifecycleOwner, { shouldNavigate ->
			if (shouldNavigate) {
				findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
				loginViewModel.onRedirectedToHome()
			}
		})

		return root
	}


	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}