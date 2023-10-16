package com.example.pokedexmvvmkotlin.ui.BindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?){
        if (!url.isNullOrEmpty()){
            Glide.with(view).load(url).into(view)
        }

    }
}