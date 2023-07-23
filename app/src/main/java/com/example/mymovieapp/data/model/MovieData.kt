package com.example.mymovieapp.data.model

data class MovieData(

    val id : Long,

    val title: String,

    val poster_path: String? = null
)

data class ResponseMovieList(

    val results: List<MovieData>
)
