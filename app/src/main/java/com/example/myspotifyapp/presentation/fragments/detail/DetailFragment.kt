package com.example.myspotifyapp.presentation.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myspotifyapp.R
import com.example.myspotifyapp.base.BaseState
import com.example.myspotifyapp.databinding.FragmentDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var mAdapter: DetailArtistAdapter
    private lateinit var mAdapter2: DetailArtistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        viewModel.getState().observe(viewLifecycleOwner, { state ->
            when (state) {

                is BaseState.Error -> {
                    updateToError(state.error)
                }

                is BaseState.Normal -> {
                    updateToNormal(state.dataNormal as SongState)
                }
                is BaseState.Loading ->
                    updateToLoading()
            }

        })
        setupView()
        viewModel.requestInformation(args.idTrack)

        return binding.root
    }

    private fun setupView() {

        mAdapter = DetailArtistAdapter(listOf())
        binding.recyclerViewArtist.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
        mAdapter2 = DetailArtistAdapter(listOf())
        binding.recyclerViewMarket.apply {
            adapter = mAdapter2
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }


    }

    private fun updateToLoading() {
        binding.progressBarDetail.visibility = View.VISIBLE
    }

    private fun updateToNormal(dataNormal: SongState) {
        dataNormal.track?.let { song ->

            val url = song.album.images[0].url
            Glide.with(requireActivity()).load(url).into(binding.imageView)

            val text="Nº " + song.track_number

            binding.textViewTrackNumber.text = text
            binding.textViewSong.text = song.name
            binding.textViewAlbum.text = song.album.name
            binding.textViewMarketTitle.text = getString(R.string.title_market)
            binding.textViewArtistTitle.text = getString(R.string.title_artist)

            val nameArtists: MutableList<String> = mutableListOf()
            song.artists.forEach { artist ->
                nameArtists.add(artist.name)
            }
            val nameMarkets: MutableList<String> = mutableListOf()
            song.available_markets.forEach { market ->
                nameMarkets.add(market)
            }
            mAdapter.updateList(nameArtists)
            mAdapter2.updateList(nameMarkets)

            if (song.available_markets.contains("ES")) {
                binding.textViewDispoinibilidad.text = getString(R.string.spain_disponiblility)
            }


            val text2 = getString(R.string.title_popularity) + song.popularity.toString()
            binding.textViewPopularity.text = text2

        }
        binding.progressBarDetail.visibility = View.GONE
    }

    private fun updateToError(error: Throwable) {

        mAdapter.updateList(listOf())
        mAdapter2.updateList(listOf())
        binding.progressBarDetail.visibility = View.GONE


        MaterialAlertDialogBuilder(requireActivity())
                .setTitle("Error de conexion")
                .setMessage("Por favor active el Wifi o los Datos Moviles")
                .setPositiveButton("Retry") { dialog, which ->
                    viewModel.requestInformation(args.idTrack)
                }
                .show()
    }

}