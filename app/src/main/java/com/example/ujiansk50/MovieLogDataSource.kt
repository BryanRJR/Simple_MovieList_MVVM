package com.example.ujiansk50

import androidx.lifecycle.LiveData
import com.example.ujiansk50.localentity.MovieEntity
import com.example.ujiansk50.localentity.TvSeriesEntity

interface MovieLogDataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getMovieById(movieId: String): LiveData<MovieEntity>

    fun getAllTvShows(): LiveData<List<TvSeriesEntity>>

    fun getTvShowById(tvShowId: String): LiveData<TvSeriesEntity>
}