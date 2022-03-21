package com.example.moviedbapp.presentation.details

import androidx.lifecycle.MutableLiveData
import com.example.moviedbapp.data.model.MovieDetailsModel
import com.example.moviedbapp.domain.GetMovieUseCaseImpl
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val useCaseImpl: GetMovieUseCaseImpl) {

    fun getMovieDetails(id: Int, apiKey: String): MutableLiveData<MovieDetailsModel> {
        return useCaseImpl.getDataMovieDetails(id = id, apiKey = apiKey)
    }
}