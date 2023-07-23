package com.example.mymovieapp.data.remote

import com.example.mymovieapp.BuildConfig
import com.example.mymovieapp.data.model.ResponseMovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @GET("movie/now_playing")
    @Headers("Authorization: Bearer $KEY")
    fun getNowPlayingMovies() : Call<ResponseMovieList>

    companion object {
        const val KEY = BuildConfig.KEY
    }
}