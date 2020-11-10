package org.marproject.moviescatalogue.data.source.remote

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.marproject.moviescatalogue.data.source.remote.network.ApiResponse
import org.marproject.moviescatalogue.data.source.remote.response.MovieResponse
import org.marproject.moviescatalogue.utils.helper.EspressoIdlingResource
import org.marproject.moviescatalogue.utils.helper.JsonHelper

class RemoteDataSource(private val jsonHelper: JsonHelper) {

    private val handler = Handler()

    companion object  {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
    }

    fun getAllMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        handler.postDelayed({
            resultMovies.value = ApiResponse.success(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultMovies
    }

    fun getAllTvShows(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        handler.postDelayed({
            resultTvShows.value = ApiResponse.success(jsonHelper.loadTvShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultTvShows
    }

}