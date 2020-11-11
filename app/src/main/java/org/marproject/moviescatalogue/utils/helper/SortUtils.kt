package org.marproject.moviescatalogue.utils.helper

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val LOWER = "Lower"
    const val HIGHER = "Higher"
    const val DEFAULT = "Default"

    fun getSortQueryMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieEntities WHERE type = 'movie' ")
        if (filter == LOWER)
            simpleQuery.append("ORDER BY rating ASC")
        else if (filter == HIGHER)
            simpleQuery.append("ORDER BY rating DESC")

        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortQueryTvShows(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieEntities WHERE type = 'tv-show' ")
        if (filter == LOWER)
            simpleQuery.append("ORDER BY rating ASC")
        else if (filter == HIGHER)
            simpleQuery.append("ORDER BY rating DESC")

        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}