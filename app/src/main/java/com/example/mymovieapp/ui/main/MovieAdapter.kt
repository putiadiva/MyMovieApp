package com.example.mymovieapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.mymovieapp.BuildConfig
import com.example.mymovieapp.data.model.MovieData
import com.example.mymovieapp.databinding.ItemMovieBinding

class MovieAdapter(private val movieList: List<MovieData>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = movieList[position].title
        val posterPath = movieList[position].poster_path
        val posterUrl = "https://image.tmdb.org/t/p/w500$posterPath"
        val glideUrl = GlideUrl(
            posterUrl,
            LazyHeaders.Builder().addHeader("Authorization", "Bearer ${BuildConfig.KEY}").build()
        )

        holder.binding.tvMovieTitle.text = title
        Glide.with(holder.itemView.context)
            .load(glideUrl)
            .into(holder.binding.ivPoster)
    }

    class ViewHolder(var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

}