package com.example.moviedbapp.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviedbapp.MovieDBApp
import com.example.moviedbapp.data.model.Result
import com.example.moviedbapp.databinding.ActivityMainBinding
import com.example.moviedbapp.presentation.details.DetailsActivity
import javax.inject.Inject

class MainActivity :
    AppCompatActivity(),
    TopRatedMoviesAdapter.OnClickItemListener,
    PopularMoviesAdapter.OnClickItemListener,
    UpcomingMoviesAdapter.OnClickItemListener {

    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var viewModel: MainViewModel
    @Inject lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter
    @Inject lateinit var popularMoviesAdapter: PopularMoviesAdapter
    @Inject lateinit var upcomingMoviesAdapter: UpcomingMoviesAdapter
    private var listTopRatedMovies: List<Result> = listOf()
    private var listPopularMovies: List<Result> = listOf()
    private var listUpcomingMovies: List<Result> = listOf()
    private val API_KEY = "2ba736e735539c701b41ef80e1593e79"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as MovieDBApp).appComponent.inject(this)

        init()
        initRefreshLayout()

        binding.swipeRefreshLayout.setOnRefreshListener {
            initRefreshLayout()
            binding.swipeRefreshLayout.isRefreshing = false
        }

    }

    private fun init() {
        topRatedMoviesAdapter.setOnItemClick(this)
        popularMoviesAdapter.setOnItemClick(this)
        upcomingMoviesAdapter.setOnItemClick(this)
        binding.rvTopRatedMovies.adapter = topRatedMoviesAdapter
        binding.rvTopRatedMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularMovies.adapter = popularMoviesAdapter
        binding.rvPopularMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvUpcomingMovies.adapter = upcomingMoviesAdapter
        binding.rvUpcomingMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original/wwemzKWzjKYJFfCeiB57q3r4Bcm.png")
            .into(binding.titleImageView)
    }

    private fun initRefreshLayout() {
        viewModel.getTopRatedMovies(apiKey = API_KEY).observe(this) { movies ->
            listTopRatedMovies = movies.results
            updateTopRatedMoviesAdapter()
        }

        viewModel.getPopularMovies(apiKey = API_KEY).observe(this) { movies ->
            listPopularMovies = movies.results
            updatePopularMoviesAdapter()
        }

        viewModel.getUpcomingMovies(apiKey = API_KEY).observe(this) { movies ->
            listUpcomingMovies = movies.results
            updateUpcomingMoviesAdapter()
        }
    }

    private fun updateTopRatedMoviesAdapter() {
        topRatedMoviesAdapter.updateList(listTopRatedMovies)
        openSwipeRefreshLayoutAndHideProgressbar()
    }

    private fun openSwipeRefreshLayoutAndHideProgressbar() {
        binding.progressBar.visibility = View.GONE
        binding.swipeRefreshLayout.visibility = View.VISIBLE
    }

    private fun updatePopularMoviesAdapter() {
        popularMoviesAdapter.updateList(listPopularMovies)
        openSwipeRefreshLayoutAndHideProgressbar()
    }

    private fun updateUpcomingMoviesAdapter() {
        upcomingMoviesAdapter.updateList(listUpcomingMovies)
        openSwipeRefreshLayoutAndHideProgressbar()
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onItemClickTopRatedMoviesAdapter(position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("OBJECT", listTopRatedMovies[position])
        startActivity(intent)
    }

    override fun onItemClickPopularMoviesAdapter(position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("OBJECT", listPopularMovies[position])
        startActivity(intent)
    }

    override fun onItemClickUpcomingMoviesAdapter(position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("OBJECT", listUpcomingMovies[position])
        startActivity(intent)
    }
}