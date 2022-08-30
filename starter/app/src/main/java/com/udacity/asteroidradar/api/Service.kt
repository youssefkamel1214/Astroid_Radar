package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Query

//this interface used to get data from server then update db
interface Nasa_Service{
//    return here is response then we try to acces response
    @GET(Constants.AsteroidGateway)
    fun get_astroeids(@Query("start_date")start_date:String,@Query("end_date")end_date:String, @Query("api_key") key: String?):
            Deferred<ResponseBody>
    @GET("planetary/apod")
    fun get_image_today(@Query("api_key") key: String?):
            Deferred<PictureOfDay>
}
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
object Network {
//    in this retorfit we used moshi make it return response in getting astroids
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    val nasaService = retrofit.create(Nasa_Service::class.java)
}