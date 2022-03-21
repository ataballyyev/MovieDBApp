package com.example.moviedbapp.domain

import androidx.lifecycle.MutableLiveData
import com.example.moviedbapp.data.model.MovieDetailsModel
import com.example.moviedbapp.data.model.MovieModel
import com.example.moviedbapp.data.model.MovieUpcomingModel
import com.example.moviedbapp.data.repository.MovieRepository
import javax.inject.Inject

class GetMovieUseCaseImpl @Inject constructor(private val repository: MovieRepository): GetMovieUseCase {

    override fun getDataTopRatedMovies(apiKey: String): MutableLiveData<MovieModel> {
        return repository.getDataTopRatedMovies(apiKey = apiKey)
    }

    override fun getDataPopularMovies(apiKey: String): MutableLiveData<MovieModel> {
        return repository.getDataPopularMovies(apiKey = apiKey)
    }

    override fun getDataUpcomingMovies(apiKey: String): MutableLiveData<MovieUpcomingModel> {
        return repository.getDataUpcomingMovies(apiKey = apiKey)
    }

    override fun getDataMovieDetails(id: Int, apiKey: String): MutableLiveData<MovieDetailsModel> {
        return repository.getMovieDetails(id = id, apiKey = apiKey)
    }
}