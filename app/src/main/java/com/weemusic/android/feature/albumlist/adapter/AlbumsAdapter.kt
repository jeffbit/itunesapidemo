package com.weemusic.android.feature.albumlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weemusic.android.R
import com.weemusic.android.feature.albumlist.model.AlbumListUIModel

class AlbumsAdapter(private var albums: List<AlbumListUIModel>) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    fun updateList(list: List<AlbumListUIModel>) {
        val diffCallBack = AlbumDiffCallBack(albums, list)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        albums = emptyList()
        albums = list
        diffResult.dispatchUpdatesTo(this)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.album_view_holder, parent, false)

        return AlbumsViewHolder(itemView)
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) =
        holder.onBind(albums[position])


    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(album: AlbumListUIModel) {

            val ivCover: ImageView = itemView.findViewById(R.id.ivCover)
            val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
            val tvArtist: TextView = itemView.findViewById(R.id.tvArtist)

            Picasso.with(itemView.context).load(album.images[0]).into(ivCover)
            tvTitle.text = album.title
            tvArtist.text = album.artist
        }
    }

    inner class AlbumDiffCallBack(
        private var oldList: List<AlbumListUIModel>,
        private var newList: List<AlbumListUIModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id ==
                    newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] ==
                    newList[newItemPosition]
        }


    }
}