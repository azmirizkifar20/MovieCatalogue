package org.marproject.moviescatalogue.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    val id: String,
    val type: String,
    val title: String,
    val year: String,
    val genre: String,
    val rating: Double,
    val description: String,
    val poster: String,
    val trailer: String
)  :  Parcelable