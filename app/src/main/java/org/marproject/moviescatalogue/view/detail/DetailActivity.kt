package org.marproject.moviescatalogue.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel
import org.marproject.moviescatalogue.R
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.databinding.ActivityDetailBinding
import org.marproject.moviescatalogue.utils.vo.Status

class DetailActivity : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityDetailBinding

    // view model
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        val extra = intent.extras
        if (extra != null) {
            val movieId = extra.getString(EXTRA_MOVIE)
            val tvShowId = extra.getString(EXTRA_TV_SHOW)

            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                viewModel.getMovieDetail().observe(this, {
                    when (it.status) {
                        Status.LOADING -> binding.loading.visibility = View.VISIBLE
                        Status.SUCCESS -> if (it.data != null) {
                            binding.loading.visibility = View.GONE
                            populateView(it.data)
                        }
                        Status.ERROR -> {
                            binding.loading.visibility = View.GONE
                            Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
            if (tvShowId != null) {
                viewModel.setSelectedTvShow(tvShowId)
                viewModel.getTvShowDetail().observe(this, {
                    when (it.status) {
                        Status.LOADING -> binding.loading.visibility = View.VISIBLE
                        Status.SUCCESS -> if (it.data != null) {
                            binding.loading.visibility = View.GONE
                            populateView(it.data)
                        }
                        Status.ERROR -> {
                            binding.loading.visibility = View.GONE
                            Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }

        setContentView(binding.root)
    }

    private fun populateView(movie: MovieEntity) {
        binding.tvTitle.text = movie.title
        binding.tvDescription.text = movie.description
        binding.tvYear.text = StringBuilder().append("Year : ").append(movie.year)
        binding.tvGenre.text = StringBuilder().append("Genre : ").append(movie.genre)
        binding.tvRating.text = StringBuilder().append("Rating : ").append(movie.rating)

        Glide.with(this)
            .load(movie.poster)
            .into(binding.imagePoster)

        binding.btnTrailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(movie.trailer)
            startActivity(intent)
        }

        binding.btnShare.setOnClickListener {
            ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setChooserTitle("Share to friend")
                .setText(getString(R.string.share_movie, movie.title, movie.trailer))
                .startChooser()
        }

        // status favorite
        var status = movie.is_favorite
        setStatusFavorite(status)

        // set bookmark
        binding.fabBookmark.setOnClickListener {
            status = !status
            setStatusFavorite(status)
            viewModel.setBookmark(movie, status)
        }

        binding.btnBack.setOnClickListener { finish() }
    }

    private fun setStatusFavorite(status: Boolean) {
        if (status)
            binding.fabBookmark.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmarked))
        else
            binding.fabBookmark.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark))
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }
}