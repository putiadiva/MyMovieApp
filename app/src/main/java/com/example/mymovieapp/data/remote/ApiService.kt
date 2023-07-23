package com.example.mymovieapp.data.remote

import com.example.mymovieapp.data.model.ResponseMovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @GET("movie/now_playing")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMTc2ZmI1MmE5OTBiMTU1NGFmNTliNmFlZjFiNjFiOCIsInN1YiI6IjY0YmJhNWUwNThlZmQzMDBhY2UxNWNlYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.y9q6g9Sruhf52iBXgZkpDJBYdOkbmRhaZR_hic0-ONg")
    fun getNowPlayingMovies() : Call<ResponseMovieList>
}