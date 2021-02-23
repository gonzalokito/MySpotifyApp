package com.example.myspotifyapp.presentation.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.myspotifyapp.R
import com.example.myspotifyapp.base.BaseState
import com.example.myspotifyapp.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(layoutInflater,container,false)

        viewModel.getState().observe(viewLifecycleOwner,{state->
            when (state){

                is BaseState.Error ->{
                    updateToError(state.error)
                }

                is BaseState.Normal ->{
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
        TODO("Not yet implemented")
    }

    private fun updateToLoading() {

    }

    private fun updateToNormal(dataNormal: SongState) {

    }

    private fun updateToError(error: Throwable) {

    }

}