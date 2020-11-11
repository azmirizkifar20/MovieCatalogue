package org.marproject.moviescatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.marproject.moviescatalogue.data.source.local.LocalDataSource
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.data.source.remote.RemoteDataSource
import org.marproject.moviescatalogue.utils.LiveDataTestUtil
import org.marproject.moviescatalogue.utils.helper.AppExecutors
import org.marproject.moviescatalogue.utils.helper.DataDummy
import org.mockito.Mockito.*

class MoviesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.remoteDummyMovies()
    private val tvShowResponses = DataDummy.remoteDummyTvShows()

    @Test
    fun getAllMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummy.moviesData()
        `when`(local.getAllMovies()).thenReturn(dummyMovies)

        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getAllMovies())
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dummyTvShow = MutableLiveData<List<MovieEntity>>()
        dummyTvShow.value = DataDummy.tvShowData()
        `when`(local.getAllTvShows()).thenReturn(dummyTvShow)

        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getAllTvShows())
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteMovies() {
        val dummyFavoriteMovies = MutableLiveData<List<MovieEntity>>()
        dummyFavoriteMovies.value = DataDummy.moviesData()
        `when`(local.getFavoriteMovies()).thenReturn(dummyFavoriteMovies)

        val favoriteMovieEntities = LiveDataTestUtil.getValue(movieRepository.getFavoriteMovies())
        verify(local).getFavoriteMovies()
        assertNotNull(favoriteMovieEntities)
        assertEquals(movieResponses.size.toLong(), favoriteMovieEntities.size.toLong())
    }

    @Test
    fun getFavoriteTvShow() {
        val dummyFavoriteTvShows = MutableLiveData<List<MovieEntity>>()
        dummyFavoriteTvShows.value = DataDummy.tvShowData()
        `when`(local.getFavoriteTvShows()).thenReturn(dummyFavoriteTvShows)

        val favoriteTvShowEntities = LiveDataTestUtil.getValue(movieRepository.getFavoriteTvShows())
        verify(local).getFavoriteTvShows()
        assertNotNull(favoriteTvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), favoriteTvShowEntities.size.toLong())
    }
}