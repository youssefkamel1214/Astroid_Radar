package com.udacity.asteroidradar.main

import android.app.Application
import android.icu.text.SimpleDateFormat
import android.util.Log
import android.view.animation.Transformation
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.Repistory.Repistory
import com.udacity.asteroidradar.api.Network
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.AteroidDoa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database= AsteroidDatabase.getInstance(application).asteroiddoa
    var _Newdate= MutableLiveData<String>(Constants.getdaysafter(7))

    val asteroids= Transformations.map(_Newdate){
             database.getasteroids(it)
    }
    val repistory=Repistory(database)
    init {
        viewModelScope.launch {
            repistory.refersh()
        }
    }
    fun change_astroid_list(choose:Int){
        when(choose){
            R.id.SHOW_ALL->_Newdate.postValue(Constants.getdaysafter(7))
            R.id.show_rent_menu->_Newdate.postValue(Constants.getdaysafter(0))
        }
    }
}