package com.example.ujiansk50.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ujiansk50.MovieLogRepository
import com.example.ujiansk50.localentity.MovieEntity

class MovieViewModel(private val movieRepository: MovieLogRepository)
    : ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = movieRepository.getAllMovies()

}