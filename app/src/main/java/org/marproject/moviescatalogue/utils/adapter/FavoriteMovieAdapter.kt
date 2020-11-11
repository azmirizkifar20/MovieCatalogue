package org.marproject.moviescatalogue.utils.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import org.marproject.moviescatalogue.R
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.view.detail.DetailActivity

class FavoriteMovieAdapter : PagedListAdapter<MovieEntity, FavoriteMovieAdapter.FavoriteMovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavoriteMovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    )

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    inner class FavoriteMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieEntity) {
            with(itemView) {
                tv_year.text = movie.year
                tv_title.text = movie.title
                tv_description.text = movie.description
                tv_rating.text = StringBuilder().append("Rating : ").append(movie.rating)

                Glide.with(itemView.context)
                    .load(movie.poster)
                    .into(image_poster)

                // on click item
                setOnClickListener {
                    if (movie.type == "movie") {
                        val intent = Intent(context, DetailActivity::class.java).apply {
                            putExtra(DetailActivity.EXTRA_MOVIE, movie.id)
                        }
                        context.startActivity(intent)
                    } else {
                        val intent = Intent(context, DetailActivity::class.java).apply {
                            putExtra(DetailActivity.EXTRA_TV_SHOW, movie.id)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}