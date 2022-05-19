package com.example.ujiansk50.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ujiansk50.MovieLogRepository
import com.example.ujiansk50.localentity.MovieEntity
import com.example.ujiansk50.localentity.TvSeriesEntity

class DetailViewModel(private val movieRepository: MovieLogRepository) : ViewModel() {

    private lateinit var Movie_Id: String
    private lateinit var Tvseries_Id: String

    fun selectedMovieId(movieId: String) {
        this.Movie_Id = movieId
    }

    fun selectedTvShowId(tvShowId: String) {
        this.Tvseries_Id = tvShowId
    }

    fun getMovieDetail(): LiveData<MovieEntity> =
        movieRepository.getMovieById(Movie_Id)

    fun getTvShowDetail(): LiveData<TvSeriesEntity> =
        movieRepository.getTvShowById(Tvseries_Id)

}