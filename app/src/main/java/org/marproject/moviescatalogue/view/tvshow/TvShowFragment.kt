package org.marproject.moviescatalogue.view.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.marproject.moviescatalogue.R
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.databinding.FragmentTvShowBinding
import org.marproject.moviescatalogue.utils.`interface`.AdapterCallback
import org.marproject.moviescatalogue.utils.adapter.AdapterUtils
import org.marproject.moviescatalogue.view.detail.DetailActivity

class TvShowFragment : Fragment() {

    // binding
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    // view model
    private val viewModel: TvShowViewModel by viewModel()

    // utils
    private lateinit var listMovies: List<MovieEntity>
    private lateinit var adapter: AdapterUtils<MovieEntity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)

        // init adapter
        adapter = AdapterUtils(requireContext())

        viewModel.getTvShowData().observe(this, {
            listMovies = it
            setupAdapter(binding.rvTvShow)
            binding.loading.visibility = View.GONE
        })

        return binding.root
    }

    private fun setupAdapter(recyclerView: RecyclerView) {
        adapter.adapterCallback(adapterCallback)
            .setLayout(R.layout.item_movie)
            .addData(listMovies)
            .isVerticalView()
            .build(recyclerView)
    }

    private val adapterCallback = object : AdapterCallback<MovieEntity> {
        override fun initComponent(itemView: View, data: MovieEntity, itemIndex: Int) {
            itemView.tv_year.text = data.year
            itemView.tv_title.text = data.title
            itemView.tv_description.text = data.description
            itemView.tv_rating.text = StringBuilder().append("Rating : ").append(data.rating)

            Glide.with(requireContext())
                .load(data.poster)
                .into(itemView.image_poster)
        }

        override fun onItemClicked(itemView: View, data: MovieEntity, itemIndex: Int) {
            startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_TV_SHOW, data.id)
            })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}