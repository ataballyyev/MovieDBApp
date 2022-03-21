package com.example.moviedbapp.presentation.details

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviedbapp.MovieDBApp
import com.example.moviedbapp.data.model.MovieDetailsModel
import com.example.moviedbapp.data.model.Result
import com.example.moviedbapp.data.utils.Helpers
import com.example.moviedbapp.databinding.ActivityDetailsBinding
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    @Inject lateinit var viewModel: DetailsViewModel
    @Inject lateinit var adapter: ProductCompaniesAdapter
    private lateinit var movieDetails: MovieDetailsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as MovieDBApp).appComponent.inject(this)

        init()

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        binding.textUrl.setOnClickListener {
            if (binding.textUrl.text.isNotEmpty()) {
                binding.functional.visibility = View.INVISIBLE
                binding.webView.visibility = View.VISIBLE
                binding.webView.loadUrl(binding.textUrl.text.toString())
                Web().setSettings(binding.webView)
                Web().setWebView(binding.webView)
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun init() {
        val detailsObject: Result = intent.getSerializableExtra("OBJECT") as Result
        initViewModel(detailsObject.id)
        binding.rvProductionCompanies.adapter = adapter
        binding.rvProductionCompanies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initViewModel(id: Int) {
        viewModel.getMovieDetails(id = id, apiKey = Helpers.API_KEY).observe(this) { movie ->
            movieDetails = movie
            initializeUI()
        }
    }

    private fun initializeUI() {
        binding.textTitle.text = movieDetails.title
        binding.textDescription.text = movieDetails.overview
        binding.textUrl.text = movieDetails.homepage
        binding.textRelease.text = "Release Date: ${movieDetails.release_date}"
        Glide.with(this)
            .load("http://image.tmdb.org/t/p/w500/${movieDetails.backdrop_path}")
            .into(binding.imageDetails)
        adapter.updateList(movieDetails.production_companies)
        binding.progressBar.visibility = View.GONE
        binding.swipeRefreshLayout.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    inner class Web {
        @SuppressLint("ObsoleteSdkInt", "SetJavaScriptEnabled")
        fun setSettings(webView: WebView) {
            webView.settings.apply {
                useWideViewPort = true
                databaseEnabled = true
                domStorageEnabled = true
                allowContentAccess = true
                javaScriptEnabled = true
                allowFileAccess = true
                displayZoomControls = true
                cacheMode = WebSettings.LOAD_DEFAULT
            }

            if (Build.VERSION.SDK_INT >= 21) {
                CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
            } else {
                CookieManager.getInstance().setAcceptCookie(true)
            }
        }

        fun setWebView(webView: WebView) {
            webView.webViewClient = object : WebViewClient() {

                @SuppressLint("ObsoleteSdkInt")
                override fun onPageFinished(view: WebView?, URL: String?) {
                    super.onPageFinished(view, URL)
                }
            }
        }
    }

}