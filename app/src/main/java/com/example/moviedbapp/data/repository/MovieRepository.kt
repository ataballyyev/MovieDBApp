package com.example.moviedbapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviedbapp.data.api.ApiService
import com.example.moviedbapp.data.model.MovieDetailsModel
import com.example.moviedbapp.data.model.MovieModel
import com.example.moviedbapp.data.model.MovieUpcomingModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val service: ApiService) {

    val topRatedMovies = MutableLiveData<MovieModel>()
    val popularMovies = MutableLiveData<MovieModel>()
    val upcomingMovies = MutableLiveData<MovieUpcomingModel>()
    val movieDetails = MutableLiveData<MovieDetailsModel>()

    fun getDataTopRatedMovies(apiKey: String): MutableLiveData<MovieModel> {
        service.getTopRatedMovies(apiKey = apiKey).enqueue(object : Callback<MovieModel>{
            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                topRatedMovies.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                Log.i("ERROR", t.message.toString())
            }
        })

        return topRatedMovies
    }

    fun getDataPopularMovies(apiKey: String): MutableLiveData<MovieModel> {
        service.getPopularMovies(apiKey = apiKey).enqueue(object : Callback<MovieModel>{
            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                popularMovies.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                Log.i("ERROR", t.message.toString())
            }
        })

        return popularMovies
    }

    fun getDataUpcomingMovies(apiKey: String): MutableLiveData<MovieUpcomingModel> {
        service.getUpcomingMovies(apiKey = apiKey).enqueue(object : Callback<MovieUpcomingModel>{
            override fun onResponse(call: Call<MovieUpcomingModel>, response: Response<MovieUpcomingModel>) {
                upcomingMovies.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieUpcomingModel>, t: Throwable) {
                Log.i("ERROR", t.message.toString())
            }
        })

        return upcomingMovies
    }

    fun getMovieDetails(id: Int, apiKey: String): MutableLiveData<MovieDetailsModel> {
        service.getMovieDetailsWithId(id = id, apiKey = apiKey).enqueue(object : Callback<MovieDetailsModel>{
            override fun onResponse(call: Call<MovieDetailsModel>, response: Response<MovieDetailsModel>) {
                movieDetails.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieDetailsModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return movieDetails
    }
}