package com.udacity.asteroidradar

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val API_QUERY_DATE_FORMAT = "yyyy-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"
//    this is used to get astroid gate way
    const val AsteroidGateway = "neo/rest/v1/feed"
//    this is used to get image of today gate way
    const val Imagetoday = "planetary/apod"
    const val Base_Api_key="Tjeuj0WJhB3jA5OjXQxvx1A5BfdKOdSbPeZZ6pzo"
    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    fun gettodayDate():String{
        val cal=Calendar.getInstance()
        val formatter=SimpleDateFormat(API_QUERY_DATE_FORMAT)
        return formatter.format(cal.time)
    }
//    this used to return date of but with adding sum days
    @SuppressLint("SimpleDateFormat")
    fun getdaysafter(Days:Int):String{
        val cal=Calendar.getInstance()
        cal.add(Calendar.DATE,Days)
        val formatter=SimpleDateFormat(API_QUERY_DATE_FORMAT)
        return formatter.format(cal.time)
    }
}