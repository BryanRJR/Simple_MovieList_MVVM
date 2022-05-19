package com.example.ujiansk50.ui.adapter

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.ujiansk50.R
import com.example.ujiansk50.databinding.ItemViewBinding
import com.example.ujiansk50.localentity.MovieEntity
import com.example.ujiansk50.ui.detail.DetailActivity
import com.example.ujiansk50.ui.detail.DetailType
import java.util.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var list_Movie = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.list_Movie.clear()
        this.list_Movie.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemCardListBinding =
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemCardListBinding)
    }

    override fun onBindViewHolder(holderMovie: MovieViewHolder, position: Int) {
        holderMovie.bind(list_Movie[position])
    }

    override fun getItemCount(): Int = list_Movie.size

    inner class MovieViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieEntity: MovieEntity) {
            with(binding) {
                title.text = movieEntity.title

                Glide.with(itemView.context)
                    .load(itemView.context.getString(R.string.baseUrlImage, movieEntity.posterPath))
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = GONE
                            return false
                        }
                    })
                    .into(poster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_TYPE, DetailType.MOVIE.ordinal)
                        putExtra(DetailActivity.EXTRA_ID, movieEntity.id)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}