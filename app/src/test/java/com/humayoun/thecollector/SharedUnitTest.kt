package com.humayoun.thecollector

import com.bumptech.glide.util.Util
import com.humayoun.thecollector.Utils.Utils
import com.humayoun.thecollector.data.category.Category
import com.humayoun.thecollector.data.category.existsIn
import org.junit.Test

import org.junit.Assert.*

class UtilUnitTest {

    @Test
    fun isValidInputTest() {
        val input1 = ""
        assertEquals(false, Utils.isValidInput(input1))

        val input2 = "somthing here.."
        assertEquals(true, Utils.isValidInput(input2))
    }

    @Test
    fun alreadyExistsTest() {
        var categories = listOf<Category>(Category("Home"), Category("Work"), Category("Vacation"))

        val category1 = Category("Home")
        assertEquals(true, category1.existsIn(categories))

        val category2 = Category("home")
        assertEquals(true, category2.existsIn(categories))

        val category3 = Category("life")
        assertEquals(false, category3.existsIn(categories))

    }


}
