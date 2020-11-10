package org.marproject.moviescatalogue.data.source

import androidx.lifecycle.LiveData
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.utils.vo.Resource

interface MoviesDataSource {
    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getDetailMovie(id: String): LiveData<Resource<MovieEntity>>
    fun getAllTvShows(): LiveData<Resource<List<MovieEntity>>>
    fun getDetailTvShow(id: String): LiveData<Resource<MovieEntity>>
    fun getFavoriteMovies(): LiveData<List<MovieEntity>>
    fun getFavoriteTvShows(): LiveData<List<MovieEntity>>
    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)
}