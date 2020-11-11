package org.marproject.moviescatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = movieDao.getMovies()

    fun getAllTvShows(): DataSource.Factory<Int, MovieEntity> = movieDao.getTvShows()

    fun getDetailMovie(id: String): LiveData<MovieEntity> = movieDao.getDetailMovie(id)

    fun getDetailTvShows(id: String): LiveData<MovieEntity> = movieDao.getDetailTvShow(id)

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = movieDao.getFavoriteMovies()

    fun getFavoriteTvShows(): DataSource.Factory<Int, MovieEntity> = movieDao.getFavoriteTvShows()

    fun getSortedMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity> = movieDao.getSortedMovies(query)

    fun getSortedTvShows(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity> = movieDao.getSortedTvShows(query)

    fun setFavorite(movie: MovieEntity, newState: Boolean) {
        movie.is_favorite = newState
        movieDao.setFavorite(movie)
    }

}