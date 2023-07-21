package com.anmp.adv160420081week4.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.anmp.adv160420081week4.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

@BindingAdapter("app:imageUrl","app:progressbar")
fun loadPhotoURL(view:ImageView,url:String?,pb:ProgressBar){
    url?.let { view.loadImage(it,pb) }
}

fun ImageView.loadImage(url: String,progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_image_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?) {
            }
})
}