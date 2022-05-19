package com.example.ujiansk50.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ujiansk50.R
import com.example.ujiansk50.databinding.ActivityDetailBinding
import com.example.ujiansk50.localentity.MovieEntity
import com.example.ujiansk50.localentity.TvSeriesEntity
import com.example.ujiansk50.ui.viewM.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TYPE = "extraType"
        const val EXTRA_ID = "extraId"
    }

    private lateinit var DetailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(DetailBinding.root)

        val type = intent.getIntExtra(EXTRA_TYPE, -1)
        val enumType: DetailType = DetailType.values()[type]

        val id = intent.getIntExtra(EXTRA_ID, -1)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        DetailBinding.progBar.visibility = View.VISIBLE
        DetailBinding.nestedScrollView.visibility = View.GONE
        when (enumType) {
            DetailType.MOVIE -> {
                viewModel.selectedMovieId(id.toString())
                viewModel.getMovieDetail().observe(this, { movie ->
                    DetailBinding.progBar.visibility = View.GONE
                    DetailBinding.nestedScrollView.visibility = View.VISIBLE
                    populateMovieDetail(movie)
                })
            }
            DetailType.TV_SHOW -> {
                viewModel.selectedTvShowId(id.toString())
                viewModel.getTvShowDetail().observe(this, { tvShow ->
                    DetailBinding.progBar.visibility = View.GONE
                    DetailBinding.nestedScrollView.visibility = View.VISIBLE
                    populateTvShowDetail(tvShow)
                })
            }
        }

        DetailBinding.backButton.setOnClickListener { finish() }
        DetailBinding.share.setOnClickListener { share() }
    }

    private fun populateMovieDetail(movieEntity: MovieEntity) {
        with(DetailBinding) {
            titleDetail.text = movieEntity.title
            release.text = movieEntity.releaseDate
            description.text = movieEntity.overview
            popular.text = getString(
                R.string.popularity_detail,
                movieEntity.popularity.toString(),
                movieEntity.voteCount.toString(),
                movieEntity.voteAverage.toString()
            )
            rating.text = movieEntity.voteAverage.toString()
            Glide.with(this@DetailActivity)
                .load(getString(R.string.baseUrlImage, movieEntity.posterPath))
                .into(imgTopBar)
            imgTopBar.tag = movieEntity.posterPath

        }
    }

    private fun populateTvShowDetail(tvShowEntity: TvSeriesEntity) {
        with(DetailBinding) {
            titleDetail.text = tvShowEntity.name
            release.text = tvShowEntity.firstAirDate
            description.text = tvShowEntity.overview
            popular.text = getString(
                R.string.popularity_detail,
                tvShowEntity.popularity.toString(),
                tvShowEntity.voteCount.toString(),
                tvShowEntity.voteAverage.toString()
            )
            rating.text = tvShowEntity.voteAverage.toString()
            Glide.with(this@DetailActivity)
                .load(getString(R.string.baseUrlImage, tvShowEntity.posterPath))
                .into(imgTopBar)
            imgTopBar.tag = tvShowEntity.posterPath
        }
    }

    private fun share() {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder.from(this).apply {
            setType(mimeType)
            setChooserTitle(getString(R.string.ttlshare))
            setText(getString(R.string.shareBody))
            startChooser()
        }
    }
}