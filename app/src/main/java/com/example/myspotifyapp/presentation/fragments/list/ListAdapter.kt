package com.example.myspotifyapp.presentation.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myspotifyapp.data.model.Album
import com.example.myspotifyapp.databinding.AlbumItemListBinding

class ListAdapter(private var myList: List<Album>, private var context: Context, private val itemListener: (item: Album) -> Any): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(val binding:AlbumItemListBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {

        val view=AlbumItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        val item= myList[position]

        holder.binding.itemSongName.text = item.name
        holder.binding.itemSongArtist.text = item.album_type
        holder.binding.itemSongDuration.text= item.total_tracks.toString()

        var url = item.images[0].url
        Glide.with(context).load(url).into(holder.binding.ivSong)

        holder.itemView.setOnClickListener {
            itemListener.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return 0
    }
    fun updateList(newList: List<Album>){
        myList=newList
        notifyDataSetChanged()
    }
}