package com.example.ujiansk50.di

import com.example.ujiansk50.MovieLogRepository
import com.example.ujiansk50.remote.RemoteData

object Injection {
    fun provideRepository(): MovieLogRepository {

        val remoteRepository = RemoteData.getInstance()

        return MovieLogRepository.getInstance(remoteRepository)
    }
}