package com.example.practicum7

import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.practicum7.TicketDetailFragment
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TicketDetailFragmentTest {

    @Test
    fun testCheckboxAndEditText() {
        val scenario = launchFragmentInContainer<TicketDetailFragment>()
        scenario.onFragment { fragment ->
            val editText = fragment.view?.findViewById<EditText>(R.id.ticket_title)
            val checkBox = fragment.view?.findViewById<CheckBox>(R.id.ticket_solved)

            // Simulate user input in the EditText
            editText?.setText("Updated Ticket Title")
            assertEquals("Updated Ticket Title", fragment.ticket.title)

            // Simulate checking the CheckBox
            checkBox?.isChecked = true
            assertEquals(true, fragment.ticket.isSolved)
        }
    }
}
