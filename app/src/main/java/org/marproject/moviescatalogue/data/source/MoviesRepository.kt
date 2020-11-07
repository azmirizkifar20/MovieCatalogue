package org.marproject.moviescatalogue.data.source

import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.data.source.remote.RemoteDataSource

class MoviesRepository private constructor(
    private val remoteDataSource: RemoteDataSource
) : MoviesDataSource {

    companion object {
        @Volatile
        private var instance: MoviesRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MoviesRepository =
            instance ?: synchronized(this) {
                instance ?: MoviesRepository(remoteData)
            }
    }

    override fun getAllMovies(): List<MovieEntity> {
        val movieResponses = remoteDataSource.getAllMovies()
        val movieList = ArrayList<MovieEntity>()

        for (response in movieResponses) {
            val movie = MovieEntity(
                id = response.id,
                type = response.type,
                title = response.title,
                year = response.year,
                genre = response.genre,
                rating = response.rating,
                description = response.description,
                poster = response.poster,
                trailer = response.trailer
            )
            movieList.add(movie)
        }

        return movieList
    }

    override fun getDetailMovie(id: String): MovieEntity {
        val moviesResponse = remoteDataSource.getAllMovies()
        lateinit var movie: MovieEntity

        for (response in moviesResponse) {
            if (response.id == id) {
                movie = MovieEntity(
                    id = response.id,
                    type = response.type,
                    title = response.title,
                    year = response.year,
                    genre = response.genre,
                    rating = response.rating,
                    description = response.description,
                    poster = response.poster,
                    trailer = response.trailer
                )
            }
        }

        return movie
    }

    override fun getAllTvShows(): List<MovieEntity> {
        val tvShowResponses = remoteDataSource.getAllTvShows()
        val tvShowList = ArrayList<MovieEntity>()

        for (response in tvShowResponses) {
            val movie = MovieEntity(
                id = response.id,
                type = response.type,
                title = response.title,
                year = response.year,
                genre = response.genre,
                rating = response.rating,
                description = response.description,
                poster = response.poster,
                trailer = response.trailer
            )
            tvShowList.add(movie)
        }

        return tvShowList
    }

    override fun getDetailTvShow(id: String): MovieEntity {
        val tvShowResponse = remoteDataSource.getAllTvShows()
        lateinit var tvShow: MovieEntity

        for (response in tvShowResponse) {
            if (response.id == id) {
                tvShow = MovieEntity(
                    id = response.id,
                    type = response.type,
                    title = response.title,
                    year = response.year,
                    genre = response.genre,
                    rating = response.rating,
                    description = response.description,
                    poster = response.poster,
                    trailer = response.trailer
                )
            }
        }

        return tvShow
    }

}