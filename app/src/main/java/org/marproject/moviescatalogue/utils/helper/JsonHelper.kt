package org.marproject.moviescatalogue.utils.helper

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import org.marproject.moviescatalogue.data.source.remote.response.MovieResponse
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun  loadMovies(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject =  JSONObject(parsingFileToString("movies_data.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val type = movie.getString("type")
                val title = movie.getString("title")
                val year  = movie.getString("year")
                val genre  = movie.getString("genre")
                val rating  = movie.getDouble("rating")
                val description  = movie.getString("description")
                val poster = movie.getString("poster")
                val trailer = movie.getString("trailer")

                val movieResponse = MovieResponse(id, type, title, year, genre, rating, description, poster, trailer)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun  loadTvShows(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject =  JSONObject(parsingFileToString("movies_data.json").toString())
            val listArray = responseObject.getJSONArray("tv_shows")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val type = movie.getString("type")
                val title = movie.getString("title")
                val year  = movie.getString("year")
                val genre  = movie.getString("genre")
                val rating  = movie.getDouble("rating")
                val description  = movie.getString("description")
                val poster = movie.getString("poster")
                val trailer = movie.getString("trailer")

                val movieResponse = MovieResponse(id, type, title, year, genre, rating, description, poster, trailer)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }
}