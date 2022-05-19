package com.example.ujiansk50.remote

import android.util.Log
import com.example.ujiansk50.network.ApiConfig
import com.example.ujiansk50.response.Movie
import com.example.ujiansk50.response.MoviesResponse
import com.example.ujiansk50.response.TvShow
import com.example.ujiansk50.response.TvShowsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteData {

    companion object {

        @Volatile
        private var instance: RemoteData? = null

        fun getInstance(): RemoteData =
            instance ?: synchronized(this) {
                instance ?: RemoteData()
            }
    }

    fun getMovies(callback: LoadMoviesCallback) {
        ApiConfig.getApiService().getMovies().enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                callback.onAllMoviesReceived(response.body()?.results)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(RemoteData::class.simpleName, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getTvShows(callback: LoadTvShowsCallback) {
        ApiConfig.getApiService().getTvShows().enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                callback.onAllTvShowsReceived(response.body()?.results)
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e(RemoteData::class.simpleName, "onFailure: ${t.message.toString()}")
            }
        })
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<Movie>?)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowResponses: List<TvShow>?)
    }
}