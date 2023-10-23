package com.example.llapp.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.llapp.databinding.FragmentHomeBinding
import com.example.llapp.databinding.FragmentLoginBinding
import com.example.llapp.ui.login.LoginViewModel

class LoginFragment : Fragment() {

	private var _binding: FragmentLoginBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

		_binding = FragmentLoginBinding.inflate(inflater, container, false)

		val root: View = binding.root

		// Set the ViewModel to your binding
		binding.vm = loginViewModel
		binding.lifecycleOwner = this

		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}