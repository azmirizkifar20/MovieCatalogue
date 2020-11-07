package org.marproject.moviescatalogue.view.detail

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.marproject.moviescatalogue.data.dummy.DataDummy

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.moviesData()[0]
    private val dummyTvShow = DataDummy.tvShowData()[0]

    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getMovieDetail() {
        val moduleEntity = viewModel.getMovieDetail()

        assertNotNull(moduleEntity)
        assertEquals(dummyMovie.id, moduleEntity?.id)
        assertEquals(dummyMovie.type, moduleEntity?.type)
        assertEquals(dummyMovie.title, moduleEntity?.title)
        assertEquals(dummyMovie.year, moduleEntity?.year)
        assertEquals(dummyMovie.genre, moduleEntity?.genre)
        assertEquals(dummyMovie.rating, moduleEntity?.rating)
        assertEquals(dummyMovie.description, moduleEntity?.description)
        assertEquals(dummyMovie.poster, moduleEntity?.poster)
        assertEquals(dummyMovie.trailer, moduleEntity?.trailer)
    }

    @Test
    fun getTvShowDetail() {
        val moduleEntity = viewModel.getTvShowDetail()

        assertNotNull(moduleEntity)
        assertEquals(dummyTvShow.id, moduleEntity?.id)
        assertEquals(dummyTvShow.type, moduleEntity?.type)
        assertEquals(dummyTvShow.title, moduleEntity?.title)
        assertEquals(dummyTvShow.year, moduleEntity?.year)
        assertEquals(dummyTvShow.genre, moduleEntity?.genre)
        assertEquals(dummyTvShow.rating, moduleEntity?.rating)
        assertEquals(dummyTvShow.description, moduleEntity?.description)
        assertEquals(dummyTvShow.poster, moduleEntity?.poster)
        assertEquals(dummyTvShow.trailer, moduleEntity?.trailer)
    }
}