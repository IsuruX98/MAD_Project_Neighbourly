package com.mad.neighbourlytest

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.MutableData
import com.google.firebase.database.Transaction
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doAnswer
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer

class FundOverviewTest {

    @Test
    fun testCalculateTotalDonations() {
        // Mock a DataSnapshot
        val snapshot = mock(DataSnapshot::class.java)
        val child1 = mock(DataSnapshot::class.java)
        val child2 = mock(DataSnapshot::class.java)
        val child3 = mock(DataSnapshot::class.java)
        val amount1 = 100
        val amount2 = 50
        val amount3 = 200
        setMockValue(child1, amount1)
        setMockValue(child2, amount2)
        setMockValue(child3, amount3)
        val children: MutableIterable<DataSnapshot> = listOf(child1, child2, child3).toMutableList()
        `when`(snapshot.children).thenReturn(children)

        // Calculate the total donations
        var totalAmount = 0
        for (childSnapshot in snapshot.children) {
            val amount = childSnapshot.getValue(Int::class.java) ?: 0
            totalAmount += amount
        }

        // Verify that the total donations match the expected value
        val expectedTotal = amount1 + amount2 + amount3
        assertEquals(expectedTotal, totalAmount)
    }

    private fun setMockValue(mock: DataSnapshot, value: Any?) {
        doAnswer(object : Answer<Any?> {
            override fun answer(invocation: InvocationOnMock): Any? {
                return value
            }
        }).`when`(mock).getValue()
    }
}
