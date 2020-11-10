package org.marproject.moviescatalogue.view.home.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import org.marproject.moviescatalogue.databinding.FragmentTvShowBinding
import org.marproject.moviescatalogue.utils.adapter.TvShowAdapter
import org.marproject.moviescatalogue.utils.vo.Status

class TvShowFragment : Fragment() {

    // binding
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    // view model
    private val viewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // adapter
        val tvShowAdapter = TvShowAdapter()

        viewModel.getTvShowData().observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> binding.loading.visibility = View.GONE
                    Status.SUCCESS -> {
                        tvShowAdapter.setTvShows(it.data)
                        tvShowAdapter.notifyDataSetChanged()
                        binding.loading.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        binding.loading.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}