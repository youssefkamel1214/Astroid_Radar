package com.udacity.asteroidradar

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.controllers.AstroidAdapter

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
        imageView.contentDescription= R.string.hazaoradastroid.toString()
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
        imageView.contentDescription= R.string.normaladastroid.toString()
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
        imageView.contentDescription= R.string.hazaoradastroid.toString()
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
        imageView.contentDescription= R.string.normaladastroid.toString()

    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}
//this used to put content descrpiton to image view and put image in image view
@BindingAdapter("showimageoftoday")
fun bindimageoftoday(imageView: ImageView,pictureOfDay: PictureOfDay?){
    pictureOfDay?.let {
        Picasso.get().load(pictureOfDay.url).placeholder(R.drawable.placeholder_picture_of_day).into(imageView)
        imageView.contentDescription=pictureOfDay.title
    }
}
//this adapter used to show load ing while getiing data from db
@BindingAdapter("showloading")
fun bindloading(progressBar: ProgressBar,asteroids: List<Asteroid>?){
    if (asteroids==null||asteroids.isEmpty())
        progressBar.visibility=View.VISIBLE
    else
        progressBar.visibility=View.GONE
}
//this biding used to update recyler adpater list when ever db updated
@BindingAdapter("updatereclerview")
fun bindupadeterecler(recyclerView: RecyclerView,asteroids: List<Asteroid>?){
    val adapter=recyclerView.adapter as AstroidAdapter
    asteroids?.let {
        adapter.submitList(asteroids)
    }
}
