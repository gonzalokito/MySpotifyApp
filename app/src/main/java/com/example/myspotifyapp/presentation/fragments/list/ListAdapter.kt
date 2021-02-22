package com.example.myspotifyapp.presentation.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myspotifyapp.databinding.AlbumItemListBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(val binding:AlbumItemListBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {

        val view=AlbumItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }
}