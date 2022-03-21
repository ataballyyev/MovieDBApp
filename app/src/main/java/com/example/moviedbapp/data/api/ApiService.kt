package com.example.moviedbapp.data.api

import com.example.moviedbapp.data.model.MovieDetailsModel
import com.example.moviedbapp.data.model.MovieModel
import com.example.moviedbapp.data.model.MovieUpcomingModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // themoviedb.org free API

    @GET("/3/movie/top_rated?")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<MovieModel>


    @GET("/3/movie/popular?")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<MovieModel>

    @GET("/3/movie/upcoming?")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<MovieUpcomingModel>

    @GET("/3/movie/{id}?")
    fun getMovieDetailsWithId(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<MovieDetailsModel>

}