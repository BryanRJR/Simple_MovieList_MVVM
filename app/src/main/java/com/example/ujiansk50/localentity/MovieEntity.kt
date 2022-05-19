package com.example.ujiansk50.localentity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    val overview: String,
    val originalLanguage: String,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val id: Int,
    val title: String,
    val voteCount: Int,
    val posterPath: String
): Parcelable