package com.example.ujiansk50.ui.tvseries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujiansk50.R
import com.example.ujiansk50.databinding.FragmentTvSeriesBinding
import com.example.ujiansk50.ui.adapter.TvSeriesAdapter
import com.example.ujiansk50.ui.viewM.ViewModelFactory

class TvSeriesFragment : Fragment() {
    private var fragTvShowsBinding: FragmentTvSeriesBinding? = null
    private val TvShowsBinding get() = fragTvShowsBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragTvShowsBinding = FragmentTvSeriesBinding.inflate(layoutInflater, container, false)
        return TvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[TvSeriesViewModel::class.java]

            val tvShowsAdapter = TvSeriesAdapter()

            TvShowsBinding.progBar.visibility = View.VISIBLE
            viewModel.getTvShows().observe(viewLifecycleOwner, { movies ->
                TvShowsBinding.progBar.visibility = View.GONE
                tvShowsAdapter.setTvShows(movies)
                tvShowsAdapter.notifyDataSetChanged()
            })

            with(TvShowsBinding.recyclerTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragTvShowsBinding = null
    }

}