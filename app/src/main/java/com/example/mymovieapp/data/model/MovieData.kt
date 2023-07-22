package com.example.mymovieapp.data.model

data class MovieData(

    val id : Long,

    val title: String
)

data class ResponseMovieList(

    val results: List<MovieData>
)
