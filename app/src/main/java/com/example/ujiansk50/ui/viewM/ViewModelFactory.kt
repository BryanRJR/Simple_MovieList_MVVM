package com.example.ujiansk50.ui.viewM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ujiansk50.MovieLogRepository
import com.example.ujiansk50.di.Injection
import com.example.ujiansk50.ui.detail.DetailViewModel
import com.example.ujiansk50.ui.movie.MovieViewModel
import com.example.ujiansk50.ui.tvseries.TvSeriesViewModel

class ViewModelFactory private constructor(private val mMovieAppRepository: MovieLogRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mMovieAppRepository) as T
            }
            modelClass.isAssignableFrom(TvSeriesViewModel::class.java) -> {
                TvSeriesViewModel(mMovieAppRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mMovieAppRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }

}