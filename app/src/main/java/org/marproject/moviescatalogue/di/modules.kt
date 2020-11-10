package org.marproject.moviescatalogue.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.LocalDataSource
import org.marproject.moviescatalogue.data.source.local.room.MovieDatabase
import org.marproject.moviescatalogue.data.source.remote.RemoteDataSource
import org.marproject.moviescatalogue.utils.helper.AppExecutors
import org.marproject.moviescatalogue.utils.helper.JsonHelper
import org.marproject.moviescatalogue.view.detail.DetailViewModel
import org.marproject.moviescatalogue.view.favorite.movies.FavoriteMovieViewModel
import org.marproject.moviescatalogue.view.favorite.tvshow.FavoriteTvShowViewModel
import org.marproject.moviescatalogue.view.home.movies.MovieViewModel
import org.marproject.moviescatalogue.view.home.tvshow.TvShowViewModel

val helperModule = module {
    factory { JsonHelper(get()) }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single {
        MoviesRepository(
            get(),
            get(),
            get()
        )
    }
}

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "Movies.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvShowViewModel(get()) }
}