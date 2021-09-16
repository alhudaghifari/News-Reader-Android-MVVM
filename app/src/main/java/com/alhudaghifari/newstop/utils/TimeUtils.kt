package com.alhudaghifari.newstop.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    /**
     * input string example : 2019-06-19T00:00:00+0700
     * output example : Monday, 19 Jan 2019
     */
    fun getDateFormatting(myDate: String): String {
        try {
            val dateString = myDate.substring(0, 10)
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateString)
            val dateNumber = SimpleDateFormat("dd", Locale.ENGLISH)
            val monthNumber = SimpleDateFormat("MM", Locale.ENGLISH)
            val yearNumber = SimpleDateFormat("yyyy", Locale.ENGLISH)
            val day = dateNumber.format(date)
            val dayString = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date)
            val month = monthNumber.format(date)
            val year = yearNumber.format(date)

            return "$dayString, $day ${getMonthNameAbbreviationFromStringNumber(month)} $year"

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    /**
     * input example : 08
     * output example : Ags (based on language used)
     */
    fun getMonthNameAbbreviationFromStringNumber(month: String): String {
        return when (month) {
            "01" -> "Jan"
            "02" -> "Feb"
            "03" -> "Mar"
            "04" -> "Apr"
            "05" -> "May"
            "06" -> "Jun"
            "07" -> "Jul"
            "08" -> "Aug"
            "09" -> "Sep"
            "10" -> "Oct"
            "11" -> "Nov"
            "12" -> "Dec"
            "1" -> "Jan"
            "2" -> "Feb"
            "3" -> "Mar"
            "4" -> "Apr"
            "5" -> "May"
            "6" -> "Jun"
            "7" -> "Jul"
            "8" -> "Aug"
            "9" -> "Sep"
            else -> ""
        }
    }
}