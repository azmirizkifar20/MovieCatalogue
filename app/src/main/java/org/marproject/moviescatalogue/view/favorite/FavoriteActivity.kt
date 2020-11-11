package org.marproject.moviescatalogue.view.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.marproject.moviescatalogue.R
import org.marproject.moviescatalogue.databinding.ActivityFavoriteBinding
import org.marproject.moviescatalogue.utils.adapter.SectionFavoriteAdapter

class FavoriteActivity : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)

        val sectionFavoriteAdapter = SectionFavoriteAdapter(this, supportFragmentManager)
        binding.viewPager.apply {
            adapter = sectionFavoriteAdapter
            binding.tabLayout.setupWithViewPager(this)
        }

        supportActionBar?.apply {
            elevation = 0f
            title = getString(R.string.favorite_movies)
            setDisplayHomeAsUpEnabled(true)
        }

        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}