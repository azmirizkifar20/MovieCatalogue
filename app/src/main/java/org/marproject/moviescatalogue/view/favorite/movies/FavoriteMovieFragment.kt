package org.marproject.moviescatalogue.view.favorite.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import org.marproject.moviescatalogue.databinding.FragmentFavoriteMovieBinding
import org.marproject.moviescatalogue.utils.adapter.MovieAdapter

class FavoriteMovieFragment : Fragment() {

    // binding
    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!

    // view model
    private val viewModel: FavoriteMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // adapter
        val favoriteMovieAdapter = MovieAdapter()

        viewModel.getFavoriteMovies().observe(this, {
            favoriteMovieAdapter.setMovies(it)
            favoriteMovieAdapter.notifyDataSetChanged()
            binding.loading.visibility = View.GONE
        })

        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteMovieAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}