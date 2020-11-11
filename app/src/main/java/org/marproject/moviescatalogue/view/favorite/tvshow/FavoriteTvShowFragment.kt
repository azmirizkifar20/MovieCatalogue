package org.marproject.moviescatalogue.view.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import org.marproject.moviescatalogue.R
import org.marproject.moviescatalogue.databinding.FragmentFavoriteTvShowBinding
import org.marproject.moviescatalogue.utils.adapter.FavoriteMovieAdapter

class FavoriteTvShowFragment : Fragment() {

    // binding
    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding!!

    // view model
    private val viewModel: FavoriteTvShowViewModel by viewModel()

    // adapter
    private lateinit var adapter: FavoriteMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvTvShow)

        // adapter
        adapter = FavoriteMovieAdapter()

        viewModel.getFavoriteTvShows().observe(this, {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
            binding.loading.visibility = View.GONE
        })

        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = this@FavoriteTvShowFragment.adapter
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return makeMovementFlags(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = adapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavorite(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                with(snackbar) {
                    setAction(R.string.message_ok) {
                        movieEntity?.let { movie -> viewModel.setFavorite(movie) }
                    }
                    show()
                }
            }
        }
    })

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}