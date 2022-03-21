package com.example.moviedbapp.presentation.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbapp.R
import com.example.moviedbapp.data.model.Result
import javax.inject.Inject

class PopularMoviesAdapter @Inject constructor(): RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    private var listResult: List<Result> = listOf()
    lateinit var onClickItemListener: OnClickItemListener

    inner class PopularMoviesViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val imageView: ImageView = view.findViewById(R.id.imageView)

        init {
            this.itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onClickItemListener.onItemClickPopularMoviesAdapter(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return PopularMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        Glide.with(holder.imageView.context)
            .load("http://image.tmdb.org/t/p/w500/${listResult[position].poster_path}")
            .into(holder.imageView)
    }

    override fun getItemCount() = listResult.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<Result>) {
        listResult = list
        notifyDataSetChanged()
    }

    fun setOnItemClick(onClick: OnClickItemListener) {
        onClickItemListener = onClick
    }

    interface OnClickItemListener {
        fun onItemClickPopularMoviesAdapter(position: Int)
    }
}