package org.marproject.moviescatalogue.view.favorite.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.utils.helper.DataDummy
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {

    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(moviesRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyFavoriteMovie = DataDummy.moviesData()
        val favoriteMovies = MutableLiveData<List<MovieEntity>>()
        favoriteMovies.value = dummyFavoriteMovie

        `when`(moviesRepository.getFavoriteMovies()).thenReturn(favoriteMovies)
        val favoriteMoviesEntities = viewModel.getFavoriteMovies().value
        verify(moviesRepository).getFavoriteMovies()
        assertNotNull(favoriteMoviesEntities)
        assertEquals(10, favoriteMoviesEntities?.size)

        viewModel.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(dummyFavoriteMovie)
    }
}