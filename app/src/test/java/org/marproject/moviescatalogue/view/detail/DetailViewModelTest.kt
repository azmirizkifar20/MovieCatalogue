package org.marproject.moviescatalogue.view.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.utils.helper.DataDummy
import org.marproject.moviescatalogue.utils.vo.Resource
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
    private lateinit var observer: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(moviesRepository)
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getMovieDetail() {
        val dummyDetailMovie = Resource.success(DataDummy.moviesData()[0])
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(moviesRepository.getDetailMovie(movieId)).thenReturn(movie)
        viewModel.movie.observeForever(observer)
        verify(observer).onChanged(dummyDetailMovie)
    }

    @Test
    fun getTvShowDetail() {
        val dummyDetailTvShow = Resource.success(DataDummy.tvShowData()[0])
        val tvShow = MutableLiveData<Resource<MovieEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(moviesRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)
        viewModel.tvShow.observeForever(observer)
        verify(observer).onChanged(dummyDetailTvShow)
    }
}