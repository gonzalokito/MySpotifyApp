package com.example.myspotifyapp.presentation.fragments.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myspotifyapp.data.model.Album
import com.example.myspotifyapp.data.model.Item
import com.example.myspotifyapp.databinding.AlbumItemListBinding
import com.example.myspotifyapp.databinding.ArtistItemListBinding

class DetailArtistAdapter(private var myList: List<String>): RecyclerView.Adapter<DetailArtistAdapter.ViewHolder>() {

    class ViewHolder(val binding:ArtistItemListBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view=ArtistItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= myList[position]

        holder.binding.itemArtist.text = item

    }

    override fun getItemCount() = myList.size

    fun updateList(newList: List<String>){
        myList=newList
        notifyDataSetChanged()
    }
}