package com.mahapp1397.borutoapp.data

import androidx.room.TypeConverter

class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String
    {
        val stringBuilder = StringBuilder()

        for (item in list)
            stringBuilder.append(item).append(separator)

        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String) = string.split(separator)

}