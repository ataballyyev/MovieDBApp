package com.example.moviedbapp

import android.app.Application
import com.example.moviedbapp.di.component.AppComponent
import com.example.moviedbapp.di.component.DaggerAppComponent

class MovieDBApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }
}