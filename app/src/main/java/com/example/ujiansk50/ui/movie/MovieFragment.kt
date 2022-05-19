package com.example.ujiansk50.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujiansk50.R
import com.example.ujiansk50.databinding.FragmentMovieBinding
import com.example.ujiansk50.ui.adapter.MovieAdapter
import com.example.ujiansk50.ui.viewM.ViewModelFactory


class MovieFragment : Fragment() {

    private var fragMoviesBinding: FragmentMovieBinding? = null
    private val MoviesBinding get() = fragMoviesBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragMoviesBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return  MoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val moviesAdapter = MovieAdapter()

            MoviesBinding.progBar.visibility = View.VISIBLE
            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                MoviesBinding.progBar.visibility = View.GONE
                moviesAdapter.setMovies(movies)
                moviesAdapter.notifyDataSetChanged()
            })

            with( MoviesBinding.recyclerMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragMoviesBinding = null
    }

}