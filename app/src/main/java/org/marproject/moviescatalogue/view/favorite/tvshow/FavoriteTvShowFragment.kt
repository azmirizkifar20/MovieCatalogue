package org.marproject.moviescatalogue.view.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import org.marproject.moviescatalogue.databinding.FragmentFavoriteTvShowBinding
import org.marproject.moviescatalogue.utils.adapter.FavoriteMovieAdapter

class FavoriteTvShowFragment : Fragment() {

    // binding
    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding!!

    // view model
    private val viewModel: FavoriteTvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // adapter
        val favoriteTvShowAdapter = FavoriteMovieAdapter()

        viewModel.getFavoriteTvShows().observe(this, {
            favoriteTvShowAdapter.submitList(it)
            favoriteTvShowAdapter.notifyDataSetChanged()
            binding.loading.visibility = View.GONE
        })

        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteTvShowAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}