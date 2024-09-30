package com.example.practicum7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicum7.databinding.FragmentTicketListBinding

class TicketListFragment : Fragment() {

    private var _binding: FragmentTicketListBinding? = null
    private val binding get() = _binding!!
    private lateinit var ticketListViewModel: TicketListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ticketListViewModel = ViewModelProvider(this).get(TicketListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTicketListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RecyclerView
        binding.ticketRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = TicketListAdapter(ticketListViewModel.tickets)
        binding.ticketRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
