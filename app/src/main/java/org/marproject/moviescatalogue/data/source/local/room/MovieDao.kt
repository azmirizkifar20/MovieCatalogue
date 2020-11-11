package org.marproject.moviescatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movieEntities WHERE type = 'movie'")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieEntities WHERE type = 'tv-show'")
    fun getTvShows(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieEntities WHERE id = :id AND type = 'movie'")
    fun getDetailMovie(id: String): LiveData<MovieEntity>

    @Query("SELECT * FROM movieEntities WHERE id = :id AND type = 'tv-show'")
    fun getDetailTvShow(id: String): LiveData<MovieEntity>

    @Query("SELECT * FROM movieEntities WHERE type = 'movie' AND is_favorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieEntities WHERE type = 'tv-show' AND is_favorite = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, MovieEntity>

    @Update
    fun setFavorite(movie: MovieEntity)

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getSortedMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getSortedTvShows(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>
}