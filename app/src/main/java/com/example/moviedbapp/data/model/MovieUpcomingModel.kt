package com.example.moviedbapp.data.model

data class MovieUpcomingModel(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)