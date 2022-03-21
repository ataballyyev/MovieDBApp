package com.example.moviedbapp.domain

import androidx.lifecycle.MutableLiveData
import com.example.moviedbapp.data.model.MovieDetailsModel
import com.example.moviedbapp.data.model.MovieModel
import com.example.moviedbapp.data.model.MovieUpcomingModel

interface GetMovieUseCase {

    fun getDataTopRatedMovies(apiKey: String): MutableLiveData<MovieModel>

    fun getDataPopularMovies(apiKey: String): MutableLiveData<MovieModel>

    fun getDataUpcomingMovies(apiKey: String): MutableLiveData<MovieUpcomingModel>

    fun getDataMovieDetails(id: Int, apiKey: String): MutableLiveData<MovieDetailsModel>
}