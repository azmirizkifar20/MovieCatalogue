package org.marproject.moviescatalogue.view.home.movies

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import org.marproject.moviescatalogue.R
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.databinding.FragmentMovieBinding
import org.marproject.moviescatalogue.utils.adapter.MovieAdapter
import org.marproject.moviescatalogue.utils.helper.SortUtils.DEFAULT
import org.marproject.moviescatalogue.utils.helper.SortUtils.HIGHER
import org.marproject.moviescatalogue.utils.helper.SortUtils.LOWER
import org.marproject.moviescatalogue.utils.vo.Status
import org.marproject.moviescatalogue.view.favorite.FavoriteActivity

class MovieFragment : Fragment() {

    // binding
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    // view model
    private val viewModel: MovieViewModel by viewModel()

    // adapter
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // adapter
        adapter = MovieAdapter()

        viewModel.getMoviesData().observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> binding.loading.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        adapter.submitList(it.data)
                        adapter.notifyDataSetChanged()
                        binding.loading.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        binding.loading.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = this@MovieFragment.adapter
        }
    }

    private val observer = Observer<PagedList<MovieEntity>> {
        if (it != null)
            adapter.submitList(it)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_sort, menu)
        inflater.inflate(R.menu.action_menu_favorite, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_lower -> sort = LOWER
            R.id.action_higher -> sort = HIGHER
            R.id.action_default -> sort = DEFAULT
            R.id.item_favorite -> startActivity(Intent(requireContext(), FavoriteActivity::class.java))
        }

        viewModel.getSortedMovies(sort).observe(this, observer)
        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}