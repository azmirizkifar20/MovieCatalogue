package org.marproject.moviescatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.sqlite.db.SupportSQLiteQuery
import org.marproject.moviescatalogue.data.source.local.LocalDataSource
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.data.source.remote.RemoteDataSource
import org.marproject.moviescatalogue.data.source.remote.network.ApiResponse
import org.marproject.moviescatalogue.data.source.remote.response.MovieResponse
import org.marproject.moviescatalogue.utils.helper.AppExecutors
import org.marproject.moviescatalogue.utils.helper.SortUtils
import org.marproject.moviescatalogue.utils.vo.Resource

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MoviesDataSource {

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()

                for (response in data) {
                    val movie = MovieEntity(
                        id = response.id,
                        type = response.type,
                        title = response.title,
                        year = response.year,
                        genre = response.genre,
                        rating = response.rating,
                        description = response.description,
                        poster = response.poster,
                        trailer = response.trailer
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()

    override fun getDetailMovie(id: String): LiveData<Resource<MovieEntity>> =
        object :  NetworkBoundResource<MovieEntity, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getDetailMovie(id)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()

                for (response in data) {
                    val movie = MovieEntity(
                        id = response.id,
                        type = response.type,
                        title = response.title,
                        year = response.year,
                        genre = response.genre,
                        rating = response.rating,
                        description = response.description,
                        poster = response.poster,
                        trailer = response.trailer
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()

    override fun getAllTvShows(): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllTvShows()

            override fun saveCallResult(data: List<MovieResponse>) {
                val tvShowList = ArrayList<MovieEntity>()

                for (response in data) {
                    val tvShow = MovieEntity(
                        id = response.id,
                        type = response.type,
                        title = response.title,
                        year = response.year,
                        genre = response.genre,
                        rating = response.rating,
                        description = response.description,
                        poster = response.poster,
                        trailer = response.trailer
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertMovies(tvShowList)
            }

        }.asLiveData()

    override fun getDetailTvShow(id: String): LiveData<Resource<MovieEntity>> =
        object :  NetworkBoundResource<MovieEntity, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getDetailTvShows(id)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllTvShows()

            override fun saveCallResult(data: List<MovieResponse>) {
                val tvShowList = ArrayList<MovieEntity>()

                for (response in data) {
                    val tvShow = MovieEntity(
                        id = response.id,
                        type = response.type,
                        title = response.title,
                        year = response.year,
                        genre = response.genre,
                        rating = response.rating,
                        description = response.description,
                        poster = response.poster,
                        trailer = response.trailer
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertMovies(tvShowList)
            }

        }.asLiveData()

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun getSortedMovies(sort: String): LiveData<PagedList<MovieEntity>> {
        val query = SortUtils.getSortQueryMovies(sort)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getSortedMovies(query), config).build()
    }

    override fun getSortedTvShows(sort: String): LiveData<PagedList<MovieEntity>> {
        val query = SortUtils.getSortQueryMovies(sort)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getSortedTvShows(query), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavorite(movie, state) }

}