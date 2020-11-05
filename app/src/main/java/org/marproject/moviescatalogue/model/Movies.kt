package org.marproject.moviescatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies (
    val id: String,
    val type: String,
    val title: String,
    val year: String,
    val genre: String,
    val rating: Double,
    val description: String,
    val poster: String,
    val trailer: String
) : Parcelable