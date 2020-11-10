package org.marproject.moviescatalogue.utils.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import org.marproject.moviescatalogue.R
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.view.detail.DetailActivity

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShows = ArrayList<MovieEntity>()

    fun setTvShows(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TvShowViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    )

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val movie = listTvShows[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listTvShows.size

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: MovieEntity) {
            with(itemView) {
                tv_year.text = tvShow.year
                tv_title.text = tvShow.title
                tv_description.text = tvShow.description
                tv_rating.text = StringBuilder().append("Rating : ").append(tvShow.rating)

                Glide.with(itemView.context)
                    .load(tvShow.poster)
                    .into(image_poster)

                // on click item
                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_TV_SHOW, tvShow.id)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }
}