package com.example.ujiansk50.localentity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvSeriesEntity(
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String,
    val popularity: Double,
    val voteAverage: Double,
    val name: String,
    val id: Int,
    val voteCount: Int,
    val posterPath: String,
): Parcelable