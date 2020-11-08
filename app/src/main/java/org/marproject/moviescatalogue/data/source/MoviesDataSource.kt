package org.marproject.moviescatalogue.data.source

import androidx.lifecycle.LiveData
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity

interface MoviesDataSource {
    fun getAllMovies(): LiveData<List<MovieEntity>>
    fun getDetailMovie(id: String): LiveData<MovieEntity>
    fun getAllTvShows(): LiveData<List<MovieEntity>>
    fun getDetailTvShow(id: String): LiveData<MovieEntity>
}