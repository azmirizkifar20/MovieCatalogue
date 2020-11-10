package org.marproject.moviescatalogue.view.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import org.marproject.moviescatalogue.R
import org.marproject.moviescatalogue.databinding.ActivityHomeBinding
import org.marproject.moviescatalogue.utils.adapter.SectionHomeAdapter
import org.marproject.moviescatalogue.view.favorite.FavoriteActivity

class HomeActivity : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        val sectionAdapter = SectionHomeAdapter(this, supportFragmentManager)
        binding.viewPager.apply {
            adapter = sectionAdapter
            binding.tabLayout.setupWithViewPager(this)
        }

        supportActionBar?.elevation = 0f
        // set content view
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu_favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.item_favorite -> {
            startActivity(
                Intent(this, FavoriteActivity::class.java)
            )
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}