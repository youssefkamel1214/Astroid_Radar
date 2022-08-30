package com.udacity.asteroidradar.Repistory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.Network
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AteroidDoa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception
//this class used to take data from api (nasa) than cach it on db
//and also used to clear old data from db
class Repistory (private val database: AteroidDoa){
    private val _image:MutableLiveData<PictureOfDay> = MutableLiveData<PictureOfDay>(null)
    val image:LiveData<PictureOfDay> get() = _image
    suspend fun refersh(){
        val retorofit=Network.nasaService
        withContext(Dispatchers.IO){
            database.deleteunwantedasteroids()
        }
        try {
            _image.value=retorofit.get_image_today(Constants.Base_Api_key).await()
            val response=retorofit.get_astroeids(Constants.gettodayDate(),Constants.getdaysafter(7),Constants.Base_Api_key).await()
            val list= parseAsteroidsJsonResult(JSONObject(response.string()))
            withContext(Dispatchers.IO){
                database.insertAll(list)
            }
        }catch (e:Exception){
            Log.d("error",e.message.toString())
        }
    }
}