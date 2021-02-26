package com.example.myspotifyapp.presentation.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myspotifyapp.base.BaseState
import com.example.myspotifyapp.databinding.FragmentListBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var mAdapter: ListAdapter
    private val viewModel: ListViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentListBinding.inflate(inflater, container, false)

        //Observer
        viewModel.getState().observe(viewLifecycleOwner, { state ->
            when (state) {

                is BaseState.Error -> {
                    updateToError(state.error)
                }

                is BaseState.Normal -> {
                    updateToNormal(state.dataNormal as ListState)
                }
                is BaseState.Loading ->
                    updateToLoading()
            }

        })

        setupView()

        viewModel.requestInformation()

        return binding.root
    }

    private fun setupView() {

        mAdapter = ListAdapter(listOf(), requireActivity()) { item ->
            findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(item.track.id))
        }


        binding.fragmentListRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }

    }

    private fun updateToLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun updateToNormal(dataNormal: ListState) {
        mAdapter.updateList((dataNormal).trackList)
        binding.progressBar.visibility = View.GONE
    }

    private fun updateToError(error: Throwable) {
        binding.progressBar.visibility = View.GONE
        mAdapter.updateList(listOf())
        MaterialAlertDialogBuilder(requireActivity())
                .setTitle("Error de conexion")
                .setMessage("Por favor active el Wifi o los Datos Moviles")
                .setPositiveButton("Retry") { dialog, which ->
                    viewModel.requestInformation()
                }
                .show()
    }

}