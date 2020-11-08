package org.marproject.moviescatalogue.view.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.marproject.moviescatalogue.utils.helper.DataDummy
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(moviesRepository)
    }

    @Test
    fun getTvShowData() {
        val dummyTvShow = DataDummy.tvShowData()
        val tvShows = MutableLiveData<List<MovieEntity>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(moviesRepository.getAllTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShowData().value
        Mockito.verify(moviesRepository).getAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities?.size)

        viewModel.getTvShowData().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}