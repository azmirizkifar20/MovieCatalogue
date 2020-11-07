package org.marproject.moviescatalogue.data.source

import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity

interface MoviesDataSource {
    fun getAllMovies(): List<MovieEntity>
    fun getDetailMovie(id: String): MovieEntity
    fun getAllTvShows(): List<MovieEntity>
    fun getDetailTvShow(id: String): MovieEntity
}