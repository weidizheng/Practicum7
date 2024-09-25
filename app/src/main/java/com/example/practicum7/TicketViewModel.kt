package com.example.practicum7

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class TicketViewModel : ViewModel() {

    val tickets = MutableLiveData<List<Ticket>>()

    init {
        tickets.value = listOf(
            Ticket(UUID.randomUUID(), "Fix login bug", Date(), false, requiresManager = false),
            Ticket(UUID.randomUUID(), "Improve UI design", Date(), true, requiresManager = true),
            Ticket(UUID.randomUUID(), "Add new feature", Date(), false, requiresManager = false)
        )
    }
}
