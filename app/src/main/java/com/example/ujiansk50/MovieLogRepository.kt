package com.example.ujiansk50

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ujiansk50.localentity.MovieEntity
import com.example.ujiansk50.localentity.TvSeriesEntity
import com.example.ujiansk50.remote.RemoteData
import com.example.ujiansk50.response.Movie
import com.example.ujiansk50.response.TvShow

class MovieLogRepository private constructor(private val remoteData: RemoteData) :
    MovieLogDataSource {

    companion object {
        @Volatile
        private var instance: MovieLogRepository? = null

        fun getInstance(remoteData: RemoteData): MovieLogRepository =
            instance ?: synchronized(this) {
                instance ?: MovieLogRepository(remoteData)
            }
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteData.getMovies(object : RemoteData.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<Movie>?) {
                val movieList = ArrayList<MovieEntity>()
                if (movieResponses != null) {
                    for (movie in movieResponses) {
                        with(movie) {
                            movieList.add(
                                MovieEntity(
                                    overview,
                                    originalLanguage,
                                    releaseDate,
                                    popularity,
                                    voteAverage,
                                    id,
                                    title,
                                    voteCount,
                                    posterPath
                                )
                            )
                        }
                    }
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getAllTvShows(): LiveData<List<TvSeriesEntity>> {
        val tvShowResults = MutableLiveData<List<TvSeriesEntity>>()
        remoteData.getTvShows(object : RemoteData.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponses: List<TvShow>?) {
                val tvShowList = ArrayList<TvSeriesEntity>()
                if (tvShowResponses != null) {
                    for (tvShow in tvShowResponses) {
                        with(tvShow) {
                            tvShowList.add(
                                TvSeriesEntity(
                                    firstAirDate,
                                    overview,
                                    originalLanguage,
                                    popularity,
                                    voteAverage,
                                    name,
                                    id,
                                    voteCount,
                                    posterPath,
                                )
                            )
                        }
                    }
                }
                tvShowResults.postValue(tvShowList)
            }
        })
        return tvShowResults
    }

    override fun getMovieById(movieId: String): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteData.getMovies(object : RemoteData.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<Movie>?) {
                lateinit var movie: MovieEntity
                if (movieResponses != null) {
                    for (movieItem in movieResponses) {
                        if (movieId == movieItem.id.toString())
                            with(movieItem) {
                                movie = MovieEntity(
                                    overview,
                                    originalLanguage,
                                    releaseDate,
                                    popularity,
                                    voteAverage,
                                    id,
                                    title,
                                    voteCount,
                                    posterPath
                                )
                            }
                    }
                }
                movieResult.postValue(movie)
            }
        })
        return movieResult
    }

    override fun getTvShowById(tvShowId: String): LiveData<TvSeriesEntity> {
        val tvShowResult = MutableLiveData<TvSeriesEntity>()
        remoteData.getTvShows(object : RemoteData.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponses: List<TvShow>?) {
                lateinit var tvShow: TvSeriesEntity
                if (tvShowResponses != null) {
                    for (tvShowItem in tvShowResponses) {
                        if (tvShowId == tvShowItem.id.toString())
                            with(tvShowItem) {
                                tvShow = TvSeriesEntity(
                                    firstAirDate,
                                    overview,
                                    originalLanguage,
                                    popularity,
                                    voteAverage,
                                    name,
                                    id,
                                    voteCount,
                                    posterPath,
                                )
                            }
                    }
                }
                tvShowResult.postValue(tvShow)
            }
        })
        return tvShowResult
    }
}