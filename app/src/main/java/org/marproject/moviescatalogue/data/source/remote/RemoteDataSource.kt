package org.marproject.moviescatalogue.data.source.remote

import org.marproject.moviescatalogue.data.source.remote.response.MovieResponse
import org.marproject.moviescatalogue.utils.helper.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object  {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this)  {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllMovies(): List<MovieResponse> = jsonHelper.loadMovies()

    fun getAllTvShows(): List<MovieResponse>  = jsonHelper.loadTvShows()
}