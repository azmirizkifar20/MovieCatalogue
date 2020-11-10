package org.marproject.moviescatalogue.view.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import org.marproject.moviescatalogue.databinding.FragmentMovieBinding
import org.marproject.moviescatalogue.utils.adapter.MovieAdapter
import org.marproject.moviescatalogue.utils.vo.Status

class MovieFragment : Fragment() {

    // binding
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    // view model
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // adapter
        val movieAdapter = MovieAdapter()

        viewModel.getMoviesData().observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> binding.loading.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        movieAdapter.setMovies(it.data)
                        movieAdapter.notifyDataSetChanged()
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
            adapter = movieAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}