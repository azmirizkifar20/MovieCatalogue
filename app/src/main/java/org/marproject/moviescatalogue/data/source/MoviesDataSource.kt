package org.marproject.moviescatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.utils.vo.Resource

interface MoviesDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShows(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(id: String): LiveData<Resource<MovieEntity>>

    fun getDetailTvShow(id: String): LiveData<Resource<MovieEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)
}