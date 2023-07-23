package com.example.mymovieapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovieapp.data.model.MovieData
import com.example.mymovieapp.data.model.ResponseMovieList
import com.example.mymovieapp.data.remote.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _movieList = MutableLiveData<List<MovieData>>()
    val movieList: LiveData<List<MovieData>> = _movieList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getMovieList()
    }

    private fun getMovieList() {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getNowPlayingMovies()
        client.enqueue(object : Callback<ResponseMovieList> {
            override fun onResponse(
                call: Call<ResponseMovieList>,
                response: Response<ResponseMovieList>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _movieList.value = response.body()?.results
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseMovieList>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}