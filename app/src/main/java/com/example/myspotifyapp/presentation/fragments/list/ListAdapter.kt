package com.example.myspotifyapp.presentation.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myspotifyapp.data.model.Item
import com.example.myspotifyapp.databinding.AlbumItemListBinding

class ListAdapter(private var myList: List<Item>, private var context: Context, private val itemListener: (item: Item) -> Any): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(val binding:AlbumItemListBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view=AlbumItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= myList[position]

        holder.binding.itemSongName.text = item.track.name ?: "N/A"
        holder.binding.itemSongArtist.text = item.track.artists[0].name ?: "N/A"
        var durationSongMinutos=((item.track.duration_ms / 1000) / 60).toString()
        var durationSongSegundos=((item.track.duration_ms / 1000) % 60).toString()
        if (durationSongSegundos.length==1){
            durationSongSegundos= "0$durationSongSegundos"
        }
        holder.binding.itemSongDuration.text = durationSongMinutos + ":" +durationSongSegundos ?: "N/A"

        var url = item.track.album.images[0].url
        Glide.with(context).load(url).into(holder.binding.ivSong)

        holder.itemView.setOnClickListener {
            itemListener.invoke(item)
        }
    }

    override fun getItemCount() = myList.size

    fun updateList(newList: List<Item>){
        myList=newList
        notifyDataSetChanged()
    }
}