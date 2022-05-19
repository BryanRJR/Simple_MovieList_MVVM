package com.example.ujiansk50.ui.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ujiansk50.MovieLogRepository
import com.example.ujiansk50.localentity.TvSeriesEntity

class TvSeriesViewModel(private val movieRepository: MovieLogRepository)
    : ViewModel() {

    fun getTvShows(): LiveData<List<TvSeriesEntity>> = movieRepository.getAllTvShows()

}