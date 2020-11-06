package org.marproject.moviescatalogue.view.movies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import org.marproject.moviescatalogue.R
import org.marproject.moviescatalogue.databinding.FragmentMovieBinding
import org.marproject.moviescatalogue.model.Movies
import org.marproject.moviescatalogue.utils.`interface`.AdapterCallback
import org.marproject.moviescatalogue.utils.adapter.AdapterUtils
import org.marproject.moviescatalogue.view.detail.DetailActivity

class MovieFragment : Fragment() {

    // binding
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    // view model
    private lateinit var viewModel: MovieViewModel

    // utils
    private lateinit var listMovies: List<Movies>
    private lateinit var adapter: AdapterUtils<Movies>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
        adapter = AdapterUtils(requireContext())
        listMovies = viewModel.getMoviewData()
        setupAdapter(binding.rvMovies)

        return binding.root
    }

    private fun setupAdapter(recyclerView: RecyclerView) {
        adapter.adapterCallback(adapterCallback)
            .setLayout(R.layout.item_movie)
            .addData(listMovies)
            .isVerticalView()
            .build(recyclerView)
    }

    private val adapterCallback = object : AdapterCallback<Movies> {
        override fun initComponent(itemView: View, data: Movies, itemIndex: Int) {
            itemView.tv_year.text = data.year
            itemView.tv_title.text = data.title
            itemView.tv_description.text = data.description
            itemView.tv_rating.text = StringBuilder().append("Rating : ").append(data.rating)

            Glide.with(requireContext())
                .load(data.poster)
                .into(itemView.image_poster)
        }

        override fun onItemClicked(itemView: View, data: Movies, itemIndex: Int) {
            startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_MOVIE, data.id)
            })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}