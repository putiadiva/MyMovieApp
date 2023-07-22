package com.example.mymovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovieapp.data.model.MovieData
import com.example.mymovieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var movieList = arrayListOf<MovieData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvMovies.layoutManager = layoutManager

        getDummyMovie()
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        val adapter = MovieAdapter(movieList)
        binding.rvMovies.adapter = adapter
    }

    private fun getDummyMovie() {
        var temp: MovieData?
        for (i in 0..20) {
            temp = MovieData(i.toLong(), "Movie $i")
            movieList.add(temp)
        }
    }
}