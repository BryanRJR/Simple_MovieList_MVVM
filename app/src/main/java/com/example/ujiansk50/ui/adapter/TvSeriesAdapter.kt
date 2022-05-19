package com.example.ujiansk50.ui.adapter

import android.content.Intent
import android.graphics.drawable.Drawable
import android.nfc.NfcAdapter.EXTRA_ID
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.ujiansk50.R
import com.example.ujiansk50.databinding.ItemViewBinding
import com.example.ujiansk50.localentity.TvSeriesEntity
import com.example.ujiansk50.ui.detail.DetailActivity
import com.example.ujiansk50.ui.detail.DetailType
import java.util.ArrayList

class TvSeriesAdapter: RecyclerView.Adapter<TvSeriesAdapter.TvShowsViewHolder>() {
    private var listTvSeries = ArrayList<TvSeriesEntity>()

    fun setTvShows(tvShows: List<TvSeriesEntity>?) {
        if (tvShows == null) return
        this.listTvSeries.clear()
        this.listTvSeries.addAll(tvShows)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val itemCardListBinding =
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemCardListBinding)
    }

    override fun onBindViewHolder(holderTvShows: TvShowsViewHolder, position: Int) {
        holderTvShows.bind(listTvSeries[position])
    }

    override fun getItemCount(): Int = listTvSeries.size

    inner class TvShowsViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShowEntity: TvSeriesEntity) {
            with(binding) {
                title.text = tvShowEntity.name

                Glide.with(itemView.context)
                    .load(
                        itemView.context.getString(
                            R.string.baseUrlImage,
                            tvShowEntity.posterPath
                        )
                    )
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }
                    })
                    .into(poster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_TYPE, DetailType.TV_SHOW.ordinal)
                        putExtra(DetailActivity.EXTRA_ID, tvShowEntity.id)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}