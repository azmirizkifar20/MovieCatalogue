package org.marproject.moviescatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movieEntities WHERE type = 'movie'")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieEntities WHERE type = 'tv-show'")
    fun getTvShows(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieEntities WHERE id = :id AND type = 'movie'")
    fun getDetailMovie(id: String): LiveData<MovieEntity>

    @Query("SELECT * FROM movieEntities WHERE id = :id AND type = 'tv-show'")
    fun getDetailTvShow(id: String): LiveData<MovieEntity>

    @Update
    fun setFavorite(movie: MovieEntity)
}