package org.marproject.moviescatalogue.view.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.marproject.moviescatalogue.utils.helper.DataDummy
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.moviesData()[0]
    private val dummyTvShow = DataDummy.tvShowData()[0]

    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(moviesRepository)
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getMovieDetail() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(moviesRepository.getDetailMovie(movieId)).thenReturn(movie)
        val moduleEntity = viewModel.getMovieDetail().value as MovieEntity
        verify(moviesRepository).getDetailMovie(movieId)

        assertNotNull(moduleEntity)
        assertEquals(dummyMovie.id, moduleEntity.id)
        assertEquals(dummyMovie.type, moduleEntity.type)
        assertEquals(dummyMovie.title, moduleEntity.title)
        assertEquals(dummyMovie.year, moduleEntity.year)
        assertEquals(dummyMovie.genre, moduleEntity.genre)
        assertEquals(dummyMovie.rating, moduleEntity.rating, 0.01)
        assertEquals(dummyMovie.description, moduleEntity.description)
        assertEquals(dummyMovie.poster, moduleEntity.poster)
        assertEquals(dummyMovie.trailer, moduleEntity.trailer)

        viewModel.getMovieDetail().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowDetail() {
        val tvShow = MutableLiveData<MovieEntity>()
        tvShow.value = dummyTvShow

        `when`(moviesRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)
        val moduleEntity = viewModel.getTvShowDetail().value as MovieEntity
        verify(moviesRepository).getDetailTvShow(tvShowId)

        assertNotNull(moduleEntity)
        assertEquals(dummyTvShow.id, moduleEntity.id)
        assertEquals(dummyTvShow.type, moduleEntity.type)
        assertEquals(dummyTvShow.title, moduleEntity.title)
        assertEquals(dummyTvShow.year, moduleEntity.year)
        assertEquals(dummyTvShow.genre, moduleEntity.genre)
        assertEquals(dummyTvShow.rating, moduleEntity.rating, 0.01)
        assertEquals(dummyTvShow.description, moduleEntity.description)
        assertEquals(dummyTvShow.poster, moduleEntity.poster)
        assertEquals(dummyTvShow.trailer, moduleEntity.trailer)

        viewModel.getTvShowDetail().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}