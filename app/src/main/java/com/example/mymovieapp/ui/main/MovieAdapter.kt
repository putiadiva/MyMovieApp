package com.example.mymovieapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.data.model.MovieData
import com.example.mymovieapp.databinding.ItemMovieBinding

class MovieAdapter(private val movieList: List<MovieData>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = movieList[position].title

        holder.binding.tvMovieTitle.text = title
    }

    class ViewHolder(var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

}