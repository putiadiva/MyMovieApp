package com.example.mymovieapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.mymovieapp.data.model.MovieData
import com.example.mymovieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var movieList = arrayListOf<MovieData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = GridLayoutManager(this, 2)
        binding.rvMovies.layoutManager = layoutManager

        viewModel = MainViewModel(application)

//        getDummyMovie()
//        setUpRecyclerView()
        setUpButton()
        setUpSwipeBehavior()
        observe()

    }

    private fun setUpSwipeBehavior() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            // Your code to make your refresh action
            // CallYourRefreshingMethod();
            // https://medium.com/@houdayer.corentin/how-to-use-swipe-to-refresh-swiperefreshlayout-99abd674c907
        }
    }
}

private fun setUpButton() {
        binding.btnRetry.setOnClickListener {
            viewModel.getMovieList()
        }
    }

    private fun observe() {
        viewModel.movieList.observe(this) {
            val adapter = MovieAdapter(it)
            binding.rvMovies.adapter = adapter
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.isError.observe(this) {
            showError(it)
        }

        viewModel.noInternet.observe(this) {
            showNoInternet(it)
        }
    }

    private fun showNoInternet(noInternet: Boolean) {
        binding.tvNoInternet.visibility = if (noInternet) View.VISIBLE else View.GONE
    }

    private fun showError(isError: Boolean) {
        binding.tvWarning.visibility = if (isError) View.VISIBLE else View.GONE
        binding.btnRetry.visibility = if (isError) View.VISIBLE else View.GONE
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
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