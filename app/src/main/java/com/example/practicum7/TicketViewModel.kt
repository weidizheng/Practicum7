package com.example.practicum7

import androidx.lifecycle.ViewModel
import java.util.*

class TicketListViewModel : ViewModel() {

    val tickets = List(100) { i ->
        Ticket(
            id = UUID.randomUUID(),
            title = "Ticket #$i",
            date = Date(),
            isSolved = i % 2 == 0
        )
    }
}
