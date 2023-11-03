package com.example.llapp.ui.masboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.llapp.Helpers.MasAppAdapter
import com.example.llapp.Helpers.OnItemClickListener
import com.example.llapp.Helpers.UserAppAdapter
import com.example.llapp.Models.UserApp
import com.example.llapp.databinding.FragmentDashboardBinding
import com.example.llapp.databinding.FragmentMasterboardBinding
import java.nio.file.attribute.UserPrincipal

class MasboardFragment : Fragment() {

	private lateinit var newRecyclerView: RecyclerView
	private lateinit var userApplications: ArrayList<UserApp>


	private var _binding: FragmentMasterboardBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val masboardViewModel =
			ViewModelProvider(this).get(MasboardViewModel::class.java)

		_binding = FragmentMasterboardBinding.inflate(inflater, container, false)
		val root: View = binding.root
		// Set the ViewModel to your binding
		binding.vm = masboardViewModel
		binding.lifecycleOwner = this

		binding.recycleViewMaster.layoutManager = LinearLayoutManager(context)
		binding.recycleViewMaster.setHasFixedSize(true)

		val adapter = MasAppAdapter()
		binding.recycleViewMaster.adapter = adapter

		masboardViewModel.masApplications.observe(viewLifecycleOwner) { userApplications ->
			adapter.setApps(userApplications)
		}

		adapter.setOnItemClickListener(object : OnItemClickListener {
			override fun onButtonClick(position: Int) {
				masboardViewModel.onButtonClick(position)
			}
		})


		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}