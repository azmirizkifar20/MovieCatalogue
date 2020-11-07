package org.marproject.moviescatalogue.view.tvshow

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.marproject.moviescatalogue.data.dummy.DataDummy
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(moviesRepository)
    }

    @Test
    fun getTvShowData() {
        Mockito.`when`(moviesRepository.getAllTvShows()).thenReturn(DataDummy.tvShowData())
        val tvShowEntities = viewModel.getTvShowData()
        Mockito.verify(moviesRepository).getAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)
    }
}