package org.marproject.moviescatalogue.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.marproject.moviescatalogue.databinding.ActivityHomeBinding
import org.marproject.moviescatalogue.utils.adapter.SectionAdapter

class HomeActivity : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        val sectionAdapter = SectionAdapter(this, supportFragmentManager)
        binding.viewPager.apply {
            adapter = sectionAdapter
            binding.tabLayout.setupWithViewPager(this)
        }

        supportActionBar?.elevation = 0f
        // set content view
        setContentView(binding.root)
    }
}