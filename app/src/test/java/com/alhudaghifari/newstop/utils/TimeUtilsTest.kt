package com.alhudaghifari.newstop.utils

import org.junit.Assert
import org.junit.Assert.*

import org.junit.Test

class TimeUtilsTest {

    @Test
    fun getDateFormatting() {
        assertEquals("Friday, 17 Sep 2021", TimeUtils.getDateFormatting("2021-09-17T01:01:00+0700"))
        assertEquals("Saturday, 18 Sep 2021", TimeUtils.getDateFormatting("2021-09-18T01:01:00+0700"))
        assertEquals("Saturday, 27 Nov 2021", TimeUtils.getDateFormatting("2021-11-27T01:01:00+0700"))
    }

    @Test
    fun getMonthNameAbbreviationFromStringNumber() {
        assertEquals("Aug", TimeUtils.getMonthNameAbbreviationFromStringNumber("8"))
        assertEquals("Jan", TimeUtils.getMonthNameAbbreviationFromStringNumber("1"))
        assertEquals("Feb", TimeUtils.getMonthNameAbbreviationFromStringNumber("2"))
        assertEquals("Jun", TimeUtils.getMonthNameAbbreviationFromStringNumber("06"))
    }
}