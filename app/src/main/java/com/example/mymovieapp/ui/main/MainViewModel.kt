package com.example.mymovieapp.ui.main

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovieapp.data.model.MovieData
import com.example.mymovieapp.data.model.ResponseMovieList
import com.example.mymovieapp.data.remote.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _movieList = MutableLiveData<List<MovieData>>()
    val movieList: LiveData<List<MovieData>> = _movieList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private val _noInternet = MutableLiveData<Boolean>()
    val noInternet: LiveData<Boolean> = _noInternet

    init {
        if (isConnectedToInternet()) {
            getMovieList()
        } else {
            _noInternet.value = false
        }
    }

    fun getMovieList() {
        _isLoading.value = true
        _isError.value = false

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
                    _isError.value = true
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseMovieList>, t: Throwable) {
                _isLoading.value = false
                _isError.value = true
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun isConnectedToInternet() : Boolean {
        val context = getApplication<Application>().applicationContext
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return connectivity != null
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}