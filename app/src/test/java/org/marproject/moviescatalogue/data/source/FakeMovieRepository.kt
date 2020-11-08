package org.marproject.moviescatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.data.source.remote.RemoteDataSource
import org.marproject.moviescatalogue.data.source.remote.response.MovieResponse

class FakeMovieRepository(
    private val remoteDataSource: RemoteDataSource
) : MoviesDataSource {

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponse: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()

                for (response in movieResponse) {
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
                movieResults.postValue(movieList)
            }
        })

        return movieResults
    }

    override fun getDetailMovie(id: String): LiveData<MovieEntity> {
        val movieData = MutableLiveData<MovieEntity>()

        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponse: List<MovieResponse>) {
                lateinit var movie: MovieEntity

                for (response in movieResponse) {
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
                movieData.postValue(movie)
            }
        })

        return movieData
    }

    override fun getAllTvShows(): LiveData<List<MovieEntity>> {
        val tvShowResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: List<MovieResponse>) {
                val tvShowList = ArrayList<MovieEntity>()

                for (response in tvShowResponse) {
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
                tvShowResults.postValue(tvShowList)
            }
        })

        return tvShowResults
    }

    override fun getDetailTvShow(id: String): LiveData<MovieEntity> {
        val tvShowData = MutableLiveData<MovieEntity>()

        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: List<MovieResponse>) {
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
                tvShowData.postValue(tvShow)
            }
        })

        return tvShowData
    }

}