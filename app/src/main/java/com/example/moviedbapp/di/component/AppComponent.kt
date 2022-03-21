package com.example.moviedbapp.di.component

import com.example.moviedbapp.di.module.NetworkModule
import com.example.moviedbapp.presentation.details.DetailsActivity
import com.example.moviedbapp.presentation.main.MainActivity
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: DetailsActivity)

}