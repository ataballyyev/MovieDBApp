package com.example.moviedbapp.presentation.main

import androidx.lifecycle.MutableLiveData
import com.example.moviedbapp.data.model.MovieModel
import com.example.moviedbapp.data.model.MovieUpcomingModel
import com.example.moviedbapp.domain.GetMovieUseCaseImpl
import javax.inject.Inject

class MainViewModel @Inject constructor(private val useCaseImpl: GetMovieUseCaseImpl) {

    fun getTopRatedMovies(apiKey: String): MutableLiveData<MovieModel> {
        return useCaseImpl.getDataTopRatedMovies(apiKey = apiKey)
    }

    fun getPopularMovies(apiKey: String): MutableLiveData<MovieModel> {
        return useCaseImpl.getDataPopularMovies(apiKey = apiKey)
    }

    fun getUpcomingMovies(apiKey: String): MutableLiveData<MovieUpcomingModel> {
        return useCaseImpl.getDataUpcomingMovies(apiKey = apiKey)
    }
}