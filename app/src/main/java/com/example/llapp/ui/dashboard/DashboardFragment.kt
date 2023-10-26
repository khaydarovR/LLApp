package com.example.llapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.llapp.Helpers.UserAppAdapter
import com.example.llapp.Models.UserApp
import com.example.llapp.databinding.FragmentDashboardBinding
import java.nio.file.attribute.UserPrincipal

class DashboardFragment : Fragment() {

	private lateinit var newRecyclerView: RecyclerView
	private lateinit var userApplications: ArrayList<UserApp>


	private var _binding: FragmentDashboardBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val dashboardViewModel =
			ViewModelProvider(this).get(DashboardViewModel::class.java)

		_binding = FragmentDashboardBinding.inflate(inflater, container, false)
		val root: View = binding.root
		// Set the ViewModel to your binding
		binding.vm = dashboardViewModel
		binding.lifecycleOwner = this

		binding.recycleView.layoutManager = LinearLayoutManager(context)
		binding.recycleView.setHasFixedSize(true)

		val adapter = UserAppAdapter()
		binding.recycleView.adapter = adapter

		dashboardViewModel.userApplications.observe(viewLifecycleOwner) { userApplications ->
			adapter.setApps(userApplications)
		}

		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}