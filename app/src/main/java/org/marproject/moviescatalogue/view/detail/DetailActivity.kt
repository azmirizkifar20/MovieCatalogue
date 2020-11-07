package org.marproject.moviescatalogue.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import org.marproject.moviescatalogue.R
import org.marproject.moviescatalogue.databinding.ActivityDetailBinding
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityDetailBinding

    // view model
    private lateinit var viewModel: DetailViewModel

    // utils
    private var bookmark = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        supportActionBar?.hide()

        val extra = intent.extras
        if (extra != null) {
            val movieId = extra.getString(EXTRA_MOVIE)
            val tvShowId = extra.getString(EXTRA_TV_SHOW)

            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                populateView(viewModel.getMovieDetail())
            }
            if (tvShowId != null) {
                viewModel.setSelectedTvShow(tvShowId)
                populateView(viewModel.getTvShowDetail())
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

        // set bookmark
        binding.fabBookmark.setOnClickListener {
            bookmark = !bookmark
            if (!bookmark) {
                binding.fabBookmark.setImageDrawable(
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_bookmark, this.theme)
                )
            } else {
                binding.fabBookmark.setImageDrawable(
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_bookmarked, this.theme)
                )
            }
        }

        binding.btnBack.setOnClickListener { finish() }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }
}