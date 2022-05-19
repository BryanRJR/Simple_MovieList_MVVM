package com.example.ujiansk50.network

import com.example.ujiansk50.BuildConfig
import com.example.ujiansk50.response.MoviesResponse
import com.example.ujiansk50.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MoviesResponse>

    @GET("tv?api_key=${BuildConfig.API_KEY}")
    fun getTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<TvShowsResponse>
}