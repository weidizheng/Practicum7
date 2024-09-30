package com.example.practicum7

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.practicum7.databinding.ListItemTicketBinding
import com.example.practicum7.databinding.ListItemManagerTicketBinding

// ViewHolder for normal tickets
class TicketHolder(private val binding: ListItemTicketBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(ticket: Ticket) {
        binding.ticketTitle.text = ticket.title

        // Format date to a readable string
        val formattedDate = DateFormat.format("MMM dd, yyyy", ticket.date)
        binding.ticketDate.text = formattedDate.toString()

        // Set visibility of solved icon
        binding.ticketSolved.visibility = if (ticket.isSolved) View.VISIBLE else View.GONE

        binding.root.setOnClickListener {
            Toast.makeText(binding.root.context, "${ticket.title} clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}

// ViewHolder for tickets that require manager attention
class ManagerTicketHolder(private val binding: ListItemManagerTicketBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(ticket: Ticket) {
        binding.ticketTitle.text = ticket.title

        // Click listener for "Contact Manager" button
        binding.contactManagerButton.setOnClickListener {
            Toast.makeText(binding.root.context, "${ticket.title} requires manager attention!", Toast.LENGTH_SHORT).show()
        }
    }
}

class TicketListAdapter(private val tickets: List<Ticket>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_NORMAL = 0
        private const val TYPE_MANAGER = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (tickets[position].requiresManager) TYPE_MANAGER else TYPE_NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_MANAGER) {
            val binding = ListItemManagerTicketBinding.inflate(inflater, parent, false)
            ManagerTicketHolder(binding)
        } else {
            val binding = ListItemTicketBinding.inflate(inflater, parent, false)
            TicketHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return tickets.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ticket = tickets[position]
        when (holder) {
            is ManagerTicketHolder -> holder.bind(ticket)
            is TicketHolder -> holder.bind(ticket)
        }
    }
}
