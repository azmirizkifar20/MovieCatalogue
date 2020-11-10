package org.marproject.moviescatalogue.data.source.local

import androidx.lifecycle.LiveData
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun getAllMovies(): LiveData<List<MovieEntity>> = movieDao.getMovies()

    fun getAllTvShows(): LiveData<List<MovieEntity>> = movieDao.getTvShows()

    fun getDetailMovie(id: String): LiveData<MovieEntity> = movieDao.getDetailMovie(id)

    fun getDetailTvShows(id: String): LiveData<MovieEntity> = movieDao.getDetailTvShow(id)

    fun getFavoriteMovies(): LiveData<List<MovieEntity>> = movieDao.getFavoriteMovies()

    fun getFavoriteTvShows(): LiveData<List<MovieEntity>> = movieDao.getFavoriteTvShows()

    fun setFavorite(movie: MovieEntity, newState: Boolean) {
        movie.is_favorite = newState
        movieDao.setFavorite(movie)
    }

}