package org.marproject.moviescatalogue.view.detail

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.marproject.moviescatalogue.data.dummy.DataDummy
import org.marproject.moviescatalogue.data.source.MoviesRepository
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

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        viewModel = DetailViewModel(moviesRepository)
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getMovieDetail() {
        `when`(moviesRepository.getDetailMovie(movieId)).thenReturn(dummyMovie)
        val moduleEntity = viewModel.getMovieDetail()
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
    }

    @Test
    fun getTvShowDetail() {
        `when`(moviesRepository.getDetailTvShow(tvShowId)).thenReturn(dummyTvShow)
        val moduleEntity = viewModel.getTvShowDetail()
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
    }
}