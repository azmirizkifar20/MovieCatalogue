package org.marproject.moviescatalogue.view.home.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieEntity>>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(moviesRepository)
    }

    @Test
    fun getMoviewData() {
        val dummyMovie = Resource.success(DataDummy.moviesData())
        val movies = MutableLiveData<Resource<List<MovieEntity>>>()
        movies.value = dummyMovie

        `when`(moviesRepository.getAllMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMoviesData().value?.data
        verify(moviesRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMoviesData().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}