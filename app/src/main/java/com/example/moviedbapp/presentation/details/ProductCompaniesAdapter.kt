package com.example.moviedbapp.presentation.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbapp.data.model.Result
import com.example.moviedbapp.R
import com.example.moviedbapp.data.model.ProductionCompany
import javax.inject.Inject

class ProductCompaniesAdapter @Inject constructor(): RecyclerView.Adapter<ProductCompaniesAdapter.ProductCompaniesViewHolder>() {

    private var listProductionCompanies: List<ProductionCompany> = listOf()

    class ProductCompaniesViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCompaniesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_production_companies, parent, false)
        return ProductCompaniesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductCompaniesViewHolder, position: Int) {
        Glide.with(holder.imageView.context)
            .load("http://image.tmdb.org/t/p/w500/${listProductionCompanies[position].logo_path}")
            .into(holder.imageView)
    }

    override fun getItemCount() = listProductionCompanies.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<ProductionCompany>) {
        listProductionCompanies = list
        notifyDataSetChanged()
    }
}