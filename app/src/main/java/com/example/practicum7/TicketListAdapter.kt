package com.example.practicum7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TicketListAdapter : ListAdapter<Ticket, RecyclerView.ViewHolder>(TicketDiffCallback()) {

    companion object {
        const val VIEW_TYPE_NORMAL = 1
        const val VIEW_TYPE_MANAGER = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).requiresManager) VIEW_TYPE_MANAGER else VIEW_TYPE_NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_MANAGER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_ticket_manager, parent, false)
            ManagerViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_ticket, parent, false)
            TicketViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ticket = getItem(position)
        if (holder is TicketViewHolder) {
            holder.bind(ticket)
        } else if (holder is ManagerViewHolder) {
            holder.bind(ticket)
        }
    }

    class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(ticket: Ticket) {
            itemView.findViewById<TextView>(R.id.ticket_title).text = ticket.title
            itemView.findViewById<TextView>(R.id.ticket_date).text = ticket.date.toString()
        }
    }

    class ManagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(ticket: Ticket) {
            itemView.findViewById<TextView>(R.id.ticket_title).text = ticket.title
            itemView.findViewById<TextView>(R.id.ticket_date).text = ticket.date.toString()
        }
    }
}

class TicketDiffCallback : DiffUtil.ItemCallback<Ticket>() {
    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem == newItem
    }
}
